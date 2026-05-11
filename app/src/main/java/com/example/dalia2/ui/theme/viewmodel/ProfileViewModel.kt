package com.example.dalia2.ui.theme.viewmodel

import com.example.dalia2.data.model.UserFullProfile
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dalia2.data.repository.DaliaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Estado da UI para a tela
data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val phone: String = "",
    val isPregnancyMode: Boolean = false
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: DaliaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            // Agora chamamos a função que unifica user + search que vimos no Controller
            val result = repository.getUserFullProfile()

            result.onSuccess { profile ->
                _uiState.update {
                    it.copy(
                        name = profile.name,
                        email = profile.email,
                        age = profile.age.toString(),
                        phone = profile.phone,
                        isPregnancyMode = profile.isPregnancyMode
                    )
                }
            }.onFailure { exception ->
                _errorMessage.value = "Erro ao carregar perfil: ${exception.message}"
                Log.e("ProfileVM", "Erro no carregamento", exception)
            }

            _isLoading.value = false
        }
    }

    fun updateUserProfile(newEmail: String, newPhone: String) {
        viewModelScope.launch {
            _isLoading.value = true
            // Por enquanto, atualizamos apenas localmente conforme sua decisão
            _uiState.update {
                it.copy(email = newEmail, phone = newPhone)
            }
            _isLoading.value = false
        }
    }
}