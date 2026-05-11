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
    private val repository: DaliaRepository,
) : ViewModel() {
    var registroSucesso by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf<Boolean?>(false)
    var errorMessage by mutableStateOf<String?>(null)
        private set
    var cycleData by mutableStateOf<CycleData?>(null)
        private set

    private val _diasMenstruacao = MutableStateFlow<List<LocalDate>>(emptyList())
    val diasMenstruacao = _diasMenstruacao.asStateFlow()

    private val _diasFertil = MutableStateFlow<List<LocalDate>>(emptyList())
    val diasFertil = _diasFertil.asStateFlow()

    private val _diaOvulacao = MutableStateFlow<LocalDate?>(null)
    val diaOvulacao = _diaOvulacao.asStateFlow()


    init{
        carregarStatusHoje()
    }

    fun carregarStatusHoje() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = repository.getCiclo()
                if (response.isSuccess) {
                    registroSucesso = true
                    val dados = response.getOrNull()
                    listarDatas(dados)
                } else {
                    registroSucesso = false
                }
                errorMessage = repository.getCiclo().exceptionOrNull()?.message
                Log.d("API_ERROR", "Erro: ")
            } catch (e: Exception) {
                errorMessage = "Falha na conexão"
                Log.d("API_ERROR", e.message.toString())
            } finally {
                isLoading = false
            }
        }
    }
    fun registrarMenstruacao() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = repository.registrarMenstruacao()
                if (response.isSuccess) {
                    val dados = response.getOrNull()
                    listarDatas(dados)
                }
                errorMessage = repository.registrarMenstruacao().exceptionOrNull()?.message
                Log.d("API_ERROR", "Erro: ")
            } catch (e: Exception) {
                errorMessage = "Falha na conexão"
                Log.d("API_ERROR", e.message.toString())
            }finally {
                isLoading = false
            }
        }
    }
    private fun listarDatas(cycleData: CycleData?) {
        if (cycleData == null) return
        val inicioM = cycleData.lastMenstruationDay
        var fimM = cycleData.fimMenstruacao

        if (fimM.isBefore(inicioM)) {
            fimM = inicioM.plusDays(4)
        }
        _diasMenstruacao.value = gerarListaDatas(cycleData.lastMenstruationDay, cycleData.fimMenstruacao)

        _diasFertil.value = gerarListaDatas(cycleData.inicioPeriodoFertil, cycleData.fimPeriodoFertil)
        _diaOvulacao.value = cycleData.diaOvulacao

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

    fun getStatusDoDia(data: LocalDate): String {
        return when {
            _diasMenstruacao.value.contains(data) -> "MENSTRUAÇÃO"
            _diaOvulacao.value == data -> "OVULAÇÃO"
            _diasFertil.value.contains(data) -> "PERIODO FERTIL"
            else -> ""
        }
    }
}