package com.example.dalia2.data.session

import com.example.dalia2.data.model.ProfileRequest
import javax.inject.Singleton

@Singleton
object UserSession {
    var profileCache: ProfileRequest? = null
}