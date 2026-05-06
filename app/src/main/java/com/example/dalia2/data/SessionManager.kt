package com.example.dalia2.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager @Inject constructor(@ApplicationContext context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "dalia_session",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveAccessToken(token: String) {
        sharedPreferences.edit().putString("accessToken", token).apply()
    }

    fun saveRefreshToken(token: String) {
        sharedPreferences.edit().putString("refreshToken", token).apply()
    }

    fun getAccessToken(): String? = sharedPreferences.getString("accessToken", null)

    fun getRefreshToken(): String? = sharedPreferences.getString("refreshToken", null)

    fun clearSession() {
        sharedPreferences.edit()
            .remove("accessToken")
            .remove("refreshToken")
            .apply()
    }
}