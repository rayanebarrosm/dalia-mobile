package com.example.dalia2.network

import android.util.Log
import com.example.dalia2.data.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response{
        Log.d("AUTH_INTERCEPTOR", "Interceptando a requisição")
        val token = sessionManager.getAccessToken()
        val request = chain.request().newBuilder()
        token?.let {
            request.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(request.build())
    }
}