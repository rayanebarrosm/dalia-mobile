package com.example.dalia2.data.repository

import android.util.Log
import android.util.Printer
import androidx.compose.ui.geometry.Rect
import com.example.dalia2.data.SessionManager
import com.example.dalia2.data.local.CicloDao
import com.example.dalia2.data.local.CicloEntity
import com.example.dalia2.data.model.Comments
import com.example.dalia2.data.model.CycleData
import com.example.dalia2.data.model.LoginRequest
import com.example.dalia2.data.model.Posts
import com.example.dalia2.data.model.ProfileCombinedResponse
import com.example.dalia2.data.model.RefreshTokenRequest
import com.example.dalia2.data.model.SearchRequest
import com.example.dalia2.data.model.SearchResponse
import com.example.dalia2.data.model.TokensResponse
import com.example.dalia2.data.model.UserFullProfile
import com.example.dalia2.data.model.UserRegistre
import com.example.dalia2.data.model.UserResponse
import com.example.dalia2.data.model.VerificationRequest
import com.example.dalia2.network.ApiService
import org.jetbrains.annotations.Async
import retrofit2.Response
import javax.inject.Inject

class DaliaRepository @Inject constructor(
    private val api: ApiService,
    private val sessionM: SessionManager,
    private val cicloDao: CicloDao
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

    suspend fun registrarMenstruacao(): Result<CycleData> {
        return try {
            val response = api.registrarMenstruacao()
            if (response.isSuccessful) {
                val menstruacaoResponse = response.body()

                if (menstruacaoResponse != null) {
                    //Salva Local
                    val entity = CicloEntity(
                        minCycleDuration = menstruacaoResponse.minCycleDuration,
                        maxCycleDuration = menstruacaoResponse.maxCycleDuration,
                        lastMenstruationDay = menstruacaoResponse.lastMenstruationDay.toString(),
                        diasDeAtraso = menstruacaoResponse.diasDeAtraso,
                        fimMenstruacao = menstruacaoResponse.fimMenstruacao.toString(),
                        inicioPeriodoFertil = menstruacaoResponse.inicioPeriodoFertil.toString(),
                        fimPeriodoFertil = menstruacaoResponse.fimPeriodoFertil.toString(),
                        diaOvulacao = menstruacaoResponse.diaOvulacao.toString()
                    )
                    cicloDao.update(entity)
                    Result.success(menstruacaoResponse) // Agora os tipos batem!
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

    suspend fun getCiclo(): Result<CycleData> {
        return try {
            val response = api.getCycle()
            if (response.isSuccessful) {
                val cicloResponse = response.body()

                if (cicloResponse != null) {

                    Result.success(cicloResponse)                } else {
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

    suspend fun getPosts(): Result<List<Posts>> {
        return try {
            val response = api.getPosts()
            Result.success(response)
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }

    suspend fun createPost(post: Posts): Result<Unit> {
        return try {
            val response = api.createPost(post)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro em criar post"))
            }
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }
    suspend fun createComment(idPost: String, comment: Comments): Result<Unit> {
        return try {
            val response = api.createComment(idPost, comment)
            Log.d("API_RESPONSE", "Olha o id: $idPost")
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro em criar comentario"))
            }
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }

    suspend fun likePost(idPost: String): Result<Unit>{
        return try{
            val response =api.likePost(idPost)
            Log.d("API_RESPONSE", "Like no: $idPost")
            if(response.isSuccessful){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error em dar like"))
            }
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }

    suspend fun unlikePost(idPost: String): Result<Unit>{
        return try{
            val response =api.unLikePost(idPost)
            Log.d("API_RESPONSE", "unlike no: $idPost")
            if(response.isSuccessful){
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error em dar like"))
            }
        } catch (e: Exception) {
            Log.e("REPO_EXCEPTION", "Falha catastrófica", e)
            Result.failure(e)
        }
    }

    suspend fun getUserFullProfile(): Result<UserFullProfile> {
        return try {
            val response = api.getUserProfile()
            if (response.isSuccessful) {
                val body:  ProfileCombinedResponse? = response.body()
                if (body != null) {
                    Result.success(
                        UserFullProfile(
                            id = body.user.id,
                            name = "${body.user.name} ${body.user.surname}",
                            email = body.user.email,
                            age = body.search.age,
                            phone = "",
                            isPregnancyMode = body.user.enable
                        )
                    )
                } else {
                    Result.failure(Exception("Corpo vazio"))
                }
            } else {
                Result.failure(Exception("Erro na API: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
