package com.example.dalia2.data.model

data class ProfileResponse(
    val user: UserRequest,
    val search: SearchData?
)
data class UserRequest(
    val name: String?,
    val surname: String?,
    val email: String?,
    val password: String? = null
)

data class SearchData(
    var age: Int,
    var useContraceptive: Boolean,
    var contraceptiveType: String?
)

data class ProfileRequest(
    val name: String?,
    val surname: String?,
    val email: String?,
    val password: String? = null,
    val search: SearchData?
)