package com.example.dalia2.data.model

data class ProfileRequest(
    val user: UserData,
    val search: SearchData?
)

data class UserData(
    val name: String,
    val surname: String,
    val email: String?,
    val password: String?
)

data class SearchData(
    var age: Int,
    var useContraceptive: Boolean,
    var contraceptiveType: String?
)