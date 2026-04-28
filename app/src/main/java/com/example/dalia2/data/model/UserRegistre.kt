package com.example.dalia2.data.model

import androidx.lifecycle.AtomicReference

data class UserRegistre(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val passConfirmation: String
)

data class UserResponse(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val enable: Boolean,
    val verificationToken: String,
    val TokenExperation: Long
)

data class VerificationRequest(
    val email: String,
    val token: Int
)

data class TokensResponse(
    val acessToken: String,
    val refreshToken:String
)

data class RefreshTokenRequest(
    val refreshToken:String
)