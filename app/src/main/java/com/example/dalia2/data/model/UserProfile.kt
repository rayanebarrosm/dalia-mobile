package com.example.dalia2.data.model

data class UserFullProfile(
    val id: String,
    val name: String,
    val email: String,
    val age: Int,
    val phone: String = "",
    val isPregnancyMode: Boolean
)

// Request para atualizar perfil
data class UpdateProfileRequest(
    val email: String,
    val phone: String
)

// Response do login que pode ser usado para pegar dados do usuário
data class LoginResponse(
    val user: UserResponse,
    val tokens: TokensResponse
)

data class ProfileCombinedResponse(
    val user: UserResponse,
    val search: SearchResponse
)

