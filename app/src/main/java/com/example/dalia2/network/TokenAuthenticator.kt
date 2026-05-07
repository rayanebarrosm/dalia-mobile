package com.example.dalia2.network

import android.util.Log
import com.example.dalia2.data.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

class TokenAuthenticator @Inject constructor(
    private val sessionManager: SessionManager,
    private val apiService: Provider<ApiService>
) : Authenticator {

    override  fun  authenticate (route: Route ?, response: Response ) : Request? {
        synchronized( this ) { // evita chamadas múltiplas
            Log.d("AUTH_TOKEN_AUTH", "Autenticando a requisição")
            val requestToken =
                response.request.header("Authorization")?.removePrefix("Bearer ")?.trim()
            val savedRefreshToken = runBlocking { sessionManager.getRefreshToken() } ?: return null

            val tokensResponse = apiService.get().refreshToken("Bearer $savedRefreshToken").execute()

            if (tokensResponse.isSuccessful) {
                val newTokens = tokensResponse.body()

                if(newTokens != null){
                    runBlocking {
                        Log.d("AUTH_TOKEN_AUTH", "Atualizando os tokens")
                        sessionManager.saveAccessToken(newTokens.token)
                        sessionManager.saveRefreshToken(newTokens.refreshToken)
                    }
                    return response.request.newBuilder()
                        .header("Authorization", "Bearer ${newTokens.token}")
                        .build()
                }
            }
            return null
        }
    }
}