package com.example.dalia2.ui.theme.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dalia2.data.model.CycleData
import com.example.dalia2.data.repository.DaliaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: DaliaRepository
) : ViewModel() {
    var registroSucesso by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set
    var cycleData by mutableStateOf<CycleData?>(null)
        private set

    private val _diasMenstruacao = MutableStateFlow<List<LocalDate>>(emptyList())
    val diasMenstruacao = _diasMenstruacao.asStateFlow()

    private val _diasFertil = MutableStateFlow<List<LocalDate>>(emptyList())
    val diasFertil = _diasFertil.asStateFlow()

    init{
        carregarStatusHoje()
    }
    fun carregarStatusHoje() {
        viewModelScope.launch {
            try {
                val response = repository.getCiclo()
                if (response.isSuccess) {
                    registroSucesso = true
                    val dados = response.getOrNull()
                    listarDatas(dados)
                } else {
                    registroSucesso = false
                }
                errorMessage = repository.registrarMenstruacao().exceptionOrNull()?.message
                Log.d("API_ERROR", "Erro: ")
            } catch (e: Exception) {
                errorMessage = "Falha na conexão"
                Log.d("API_ERROR", e.message.toString())
            } finally {
                registroSucesso = false
            }
        }
    }
    fun registrarMenstruacao() {
        viewModelScope.launch {
            try {
                val response = repository.registrarMenstruacao()
                if (response.isSuccess) {
                    val dados = response.getOrNull()
                    listarDatas(dados)
                }
                errorMessage = repository.getCiclo().exceptionOrNull()?.message
                Log.d("API_ERROR", "Erro: ")
            } catch (e: Exception) {
                errorMessage = "Falha na conexão"
                Log.d("API_ERROR", e.message.toString())
            }
        }
    }
    private fun listarDatas(cycleData: CycleData?) {
        if (cycleData == null) return

        _diasMenstruacao.value = gerarListaDatas(cycleData.lastMenstruationDay, cycleData.fimMenstruacao)

        _diasFertil.value = gerarListaDatas(cycleData.inicioPeriodoFertil, cycleData.fimPeriodoFertil)
    }

    private fun gerarListaDatas(inicio: LocalDate, fim: LocalDate): List<LocalDate> {
        val datas = mutableListOf<LocalDate>()
        var atual = inicio
        while (!atual.isAfter(fim)) {
            datas.add(atual)
            atual = atual.plusDays(1)
        }
        return datas
    }
}