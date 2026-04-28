package com.example.dalia2.network

import com.example.dalia2.data.model.Posts
import com.example.dalia2.data.model.UserRegistre
import com.example.dalia2.data.model.UserResponse
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


    //FORUM
    @GET("/api/posts/getTodos")
    suspend fun getPosts (
    ): List<Posts>


}