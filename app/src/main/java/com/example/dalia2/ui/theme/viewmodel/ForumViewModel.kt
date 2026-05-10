package com.example.dalia2.ui.theme.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dalia2.data.model.Posts
import com.example.dalia2.data.repository.DaliaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ForumViewModel  @Inject constructor(
    private val repository: DaliaRepository
): ViewModel(){
    var postSucess by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf<Boolean?>(false)
    var errorMessage by mutableStateOf<String?>(null)
        private set
    init{
        carregarPosts()
    }

    private val _posts = MutableStateFlow<List<Posts>>(emptyList())
    val posts = _posts.asStateFlow()

    fun carregarPosts() {
        viewModelScope.launch {
        isLoading = true
        repository.getPosts()
            .onSuccess { lista ->
                _posts.value = lista.sortedByDescending { it.createdAt }
            }
            .onFailure {
                errorMessage = it.message
            }
        isLoading = false
    }

    fun criarNovoPost(titulo: String, conteudo: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val novoPost = Posts(
                idPost = "", // API gera
                idUser = "id_USER",
                title = titulo,
                content = conteudo,
                likesValue = 0,
                createdAt = "${LocalDate.now()}",
                comments = emptyList()
            )
            repository.createPost(novoPost)
            carregarPosts()
            onSuccess()// Recarrega a lista
        }
    }
}
}