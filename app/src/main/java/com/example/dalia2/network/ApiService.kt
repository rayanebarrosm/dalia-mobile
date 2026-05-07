package com.example.dalia2.network

import com.example.dalia2.data.model.CycleData
import com.example.dalia2.data.model.LoginRequest
import com.example.dalia2.data.model.Posts
import com.example.dalia2.data.model.SearchRequest
import com.example.dalia2.data.model.SearchResponse
import com.example.dalia2.data.model.TokensResponse
import com.example.dalia2.data.model.UserRegistre
import com.example.dalia2.data.model.UserResponse
import com.example.dalia2.data.model.VerificationRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

//Aqui fica os edpoitns criados na api
interface ApiService {
    //USER
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

    @POST("/api/user/refresh")
    fun refreshToken(
        @Header("Authorization") token: String,
    ): Call<TokensResponse>

    @Headers("Content-Type: application/json",
    "Accept: */*",
    "User-Agent: PostmanRuntime/7.41.1")
    @POST("/api/user/search")
    suspend fun search(
        @Body request: SearchRequest
    ): Response<SearchResponse>

    //CALENDARIO
    @Headers("Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.41.1")
    @PUT("/api/ciclo/registrar-menstruacao")
    suspend fun registrarMenstruacao(
    ): Response<CycleData>

    @Headers("Content-Type: application/json",
        "Accept: */*",
        "User-Agent: PostmanRuntime/7.41.1")
    @GET("/api/ciclo/status")
    suspend fun getCycle(
    ): Response<CycleData>


    //FORUM
    @GET("/api/posts/getTodos")
    suspend fun getPosts (
    ): List<Posts>


}