package com.example.dalia2.ui.theme.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dalia2.data.model.ProfileRequest
import com.example.dalia2.data.repository.DaliaRepository
import com.example.dalia2.data.session.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: DaliaRepository
) : ViewModel() {

    var _uiState by mutableStateOf<ProfileRequest?>(null)


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadUserProfile() {
        viewModelScope.launch {
            if(UserSession.profileCache == null){
                _isLoading.value = true
                val response = repository.getUserFullProfile()
                if (response.isSuccess) {
                    UserSession.profileCache = response.getOrNull()
                    _uiState = UserSession.profileCache
                }
                _isLoading.value = false
            }else{
                _uiState = UserSession.profileCache
            }
        }
    }

    fun updateUserProfile(userRegistre : ProfileRequest, onSuccess: () ->Unit) {
        viewModelScope.launch {
            val response = repository.updatePerfil(userRegistre)
            if (response.isSuccess) {
                val updatedUser = response.getOrNull()

                UserSession.profileCache = UserSession.profileCache?.copy(
                    user = updatedUser!!
                )

                UserSession.profileCache = UserSession.profileCache
                onSuccess()
            }
        }
    }
}