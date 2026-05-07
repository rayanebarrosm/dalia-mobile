package com.example.dalia2.ui.theme.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dalia2.data.model.LoginRequest
import com.example.dalia2.data.repository.DaliaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: DaliaRepository,
): ViewModel() {
    private val _uiState = MutableStateFlow(LoginRequest("", ""))

    var isLoading by mutableStateOf(false)
        private set

    var loginSucess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onEmailChanged(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChanged(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun onLoginClick(email: String, password: String) {
        errorMessage = null
        if (email.isEmpty()) {
            errorMessage = "Email é obrigatorio"
            return
        } else if (password.isEmpty()) {
            errorMessage = "Senha é obrigatoria"
            return
        }
        isLoading = true
        viewModelScope.launch {
            try {
                val request = _uiState.value.copy(email = email, password = password)
                val response = repository.login(request)
                Log.d("TESTE", "tentando logar")
                if (response.isSuccess) {
                    loginSucess = true
                    Log.d("API_SUCESS", "Usuario logado")
                } else {
                    loginSucess = false
                    errorMessage = repository.login(request).exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                Log.d("API_ERROR", e.message.toString())
                errorMessage = "Falha ao fazer login"
            } finally {
                isLoading = false
            }
        }
    }
}