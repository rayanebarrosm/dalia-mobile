package com.example.dalia2.data.repository

import android.util.Log
import android.util.Printer
import androidx.compose.ui.geometry.Rect
import com.example.dalia2.data.SessionManager
import com.example.dalia2.data.model.LoginRequest
import com.example.dalia2.data.model.Posts
import com.example.dalia2.data.model.RefreshTokenRequest
import com.example.dalia2.data.model.SearchRequest
import com.example.dalia2.data.model.SearchResponse
import com.example.dalia2.data.model.TokensResponse
import com.example.dalia2.data.model.UserRegistre
import com.example.dalia2.data.model.UserResponse
import com.example.dalia2.data.model.VerificationRequest
import com.example.dalia2.network.ApiService
import org.jetbrains.annotations.Async
import retrofit2.Response
import javax.inject.Inject

class DaliaRepository @Inject constructor(
    private val api: ApiService,
    private val sessionM: SessionManager
) {
    suspend fun createUser(userRegistre: UserRegistre): Result<UserResponse> {
        return try {
            val response = api.createUser(userRegistre)
            if (response.isSuccessful) {
                val userResponse = response.body()
                if (userResponse != null) {
                    Result.success(userResponse) // Agora os tipos batem!
                } else {
                    Result.failure(Exception("Corpo da resposta vazio"))
                }
            } else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
                val cleanMessage = errorBody.replace(Regex("""\d{3}:\s*"""),"").replace("}","")

                Log.e("REPO_ERROR", "Código: $errorCode | Mensagem: $errorBody")

                Result.failure(Exception(cleanMessage))
            }
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }

    suspend fun verifiyCode( verificationRequest: VerificationRequest): Result<TokensResponse>{
        return try {
            val response = api.verifyCode(verificationRequest)
            if (response.isSuccessful) {
                // Se o backend retorna um JSON, o .string() lê o texto puro dele
                val tokens = response.body()
                if(tokens != null){
                    sessionM.saveAccessToken(tokens.token)
                    sessionM.saveRefreshToken(tokens.refreshToken)
                    Result.success(tokens)
                }else{
                    Result.failure(Exception("Resposta vazia"))
                }
            } else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
                val cleanMessage = errorBody.replace(Regex("""\d{3}:\s*"""),"").replace("}","")

                Log.e("REPO_ERROR", "Código: $errorCode | Mensagem: $errorBody")

                Result.failure(Exception(cleanMessage))
            }
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }

    suspend fun login(LoginRequest: LoginRequest): Result<TokensResponse>{
        return try {
            val response = api.login(LoginRequest)
            if(response.isSuccessful){
                val tokens = response.body()
                if(tokens != null){
                    sessionM.saveAccessToken(tokens.token)
                    sessionM.saveRefreshToken(tokens.refreshToken)
                    Result.success(tokens)
                } else {
                    Result.failure(Exception("Resposta vazia"))
                }
            }else{
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
                val cleanMessage = errorBody.replace(Regex("""\d{3}:\s*"""),"").replace("}","")
                Log.e("REPO_ERROR", "Código: $errorCode | Mensagem: $errorBody")

                Result.failure(Exception(cleanMessage))
            }
        }catch(e: Exception){
            Log.d("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }
    suspend fun search(SearchRequest: SearchRequest): Result<SearchResponse> {
        return try {
            val response = api.search(SearchRequest)
            if (response.isSuccessful) {
                val searchResponse = response.body()

                if (searchResponse != null) {
                    Result.success(searchResponse) // Agora os tipos batem!
                } else {
                    Result.failure(Exception("Corpo da resposta vazio"))
                }
            } else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
                val cleanMessage = errorBody.replace(Regex("""\d{3}:\s*"""), "").replace("}", "")

                Log.e("REPO_ERROR", "Código: $errorCode | Mensagem: $errorBody")
                Result.failure(Exception(cleanMessage))
            }
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }
}
