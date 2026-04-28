package com.example.dalia2.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dalia2.repository.DaliaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val repository: DaliaRepository
): ViewModel(){

}