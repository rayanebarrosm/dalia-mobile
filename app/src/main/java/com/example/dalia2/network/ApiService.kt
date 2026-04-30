package com.example.dalia2.network

import com.example.dalia2.data.model.LoginRequest
import com.example.dalia2.data.model.Posts
import com.example.dalia2.data.model.TokensResponse
import com.example.dalia2.data.model.UserRegistre
import com.example.dalia2.data.model.UserResponse
import com.example.dalia2.data.model.VerificationRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//Aqui fica os edpoitns criados na api
interface ApiService {
    //CADASTRO
    @POST("/api/user/criarUsuario")
    suspend fun createUser(
        @Body request: UserRegistre
    ): Response<UserResponse>

    //Verificação de email
    @POST("/api/user/verify")
    suspend fun verifyCode(
        @Body request: VerificationRequest
    ): Response<TokensResponse>

    @POST("/api/user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<TokensResponse>


    //FORUM
    @GET("/api/posts/getTodos")
    suspend fun getPosts (
    ): List<Posts>


}