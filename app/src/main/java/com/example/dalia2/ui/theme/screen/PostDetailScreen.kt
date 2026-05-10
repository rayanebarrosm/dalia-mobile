package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.ui.theme.BlueButton
import com.example.dalia2.ui.theme.viewmodel.ForumViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postId: String?,
    viewModel: ForumViewModel,
    onBack: () -> Unit
) {
    // Busca o post específico na lista do ViewModel
    val post = viewModel.posts.collectAsState().value.find { it.idPost == postId }
    var novoComentario by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conversa", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "") }
                }
            )
        },
        bottomBar = {
            // BARRA DE COMENTÁRIO FIXA EMBAIXO
            Surface(tonalElevation = 8.dp, modifier = Modifier.imePadding()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = novoComentario,
                        onValueChange = { novoComentario = it },
                        placeholder = { Text("Escreva um comentário...") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(24.dp),
                        maxLines = 3
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (novoComentario.isNotBlank() && postId != null) {
                                viewModel.addComentario(postId, novoComentario)
                                novoComentario = "" // Limpa o campo
                            }
                        },
                        enabled = novoComentario.isNotBlank()
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Enviar", tint = BlueButton)
                    }
                }
            }
        }
    ) { padding ->
        if (post == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = BlueButton)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                // 1. O CONTEÚDO DO POST
                item {
                    Text(text = post.title ?: "", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = post.content ?: "", fontSize = 16.sp, color = Color.DarkGray)
                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                    Text(text = "Comentários (${post.comments?.size ?: 0})", fontWeight = FontWeight.Bold)
                }

                // 2. LISTA DE COMENTÁRIOS
                items(post.comments ?: emptyList()) { comment ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = comment.idUser ?: "Usuária Dália", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Text(text = comment.comment ?: "", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}