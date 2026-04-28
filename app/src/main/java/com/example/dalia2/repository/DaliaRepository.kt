package com.example.dalia2.repository

import com.example.dalia2.data.model.UserRegistre
import com.example.dalia2.data.model.UserResponse
import com.example.dalia2.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class DaliaRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun createUser(UserRegistre: UserRegistre): Response<UserResponse> {
        return api.createUser(UserRegistre)
    }
}
