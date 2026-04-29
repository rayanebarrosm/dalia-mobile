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
    val enable: Boolean,
    val verificationToken: String,
    val TokenExperation: Long
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class VerificationRequest(
    val email: String,
    val token: String
)

data class TokensResponse(
    val token: String,
    val refreshToken:String
)

data class RefreshTokenRequest(
    val refreshToken:String
)