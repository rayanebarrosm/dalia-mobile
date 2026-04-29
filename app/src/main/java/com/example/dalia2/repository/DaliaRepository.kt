package com.example.dalia2.repository

import android.util.Log
import androidx.compose.ui.geometry.Rect
import com.example.dalia2.data.model.LoginRequest
import com.example.dalia2.data.model.TokensResponse
import com.example.dalia2.data.model.UserRegistre
import com.example.dalia2.data.model.UserResponse
import com.example.dalia2.data.model.VerificationRequest
import com.example.dalia2.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class DaliaRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun createUser(UserRegistre: UserRegistre): Response<UserResponse> {
        return api.createUser(UserRegistre)
    }

    suspend fun verifiyCode( verificationRequest: VerificationRequest): Result<TokensResponse>{
        return try {
            val response = api.verifyCode(verificationRequest)
            if (response.isSuccessful) {
                // Se o backend retorna um JSON, o .string() lê o texto puro dele
                val tokens = response.body()
                if(tokens != null){
                    Result.success(tokens)
                }else{
                    Result.failure(Exception("Resposta vazia"))
                }
            } else {
                val errorCode = response.code()
                val errorMsg = response.errorBody()?.string() ?: "Erro desconhecido"
                Log.e("REPO_ERROR", "Código: $errorCode | Mensagem: $errorMsg")

                Result.failure(Exception("Erro $errorCode: $errorMsg"))
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
                    Result.success(tokens)
                } else {
                    Result.failure(Exception("Resposta vazia"))
                }
            }else{
                val errorCode = response.code()
                val errorMsg = response.errorBody()?.string() ?: "Erro desconhecido"
                Log.e("REPO_ERROR", "Código: $errorCode | Mensagem: $errorMsg")

                Result.failure(Exception("Erro $errorCode: $errorMsg"))
            }
        }catch(e: Exception){
            Log.d("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }
}
