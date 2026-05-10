package com.example.dalia2.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.data.model.Posts
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.BlueButton
import com.example.dalia2.ui.theme.Black
import com.example.dalia2.ui.theme.viewmodel.ForumViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(
    viewModel: ForumViewModel,
    onToCreatePost: () -> Unit ={},
    onNavigateToPostDetail: (String) -> Unit = {}
) {
    var textoBusca by remember { mutableStateOf("") }
    val postsAPI by viewModel.posts.collectAsState()
    val postsFiltrados = postsAPI.filter {
        (it.title?.contains(textoBusca, ignoreCase = true) ?: false) || textoBusca.isEmpty()
    }

    LaunchedEffect(Unit) {
        viewModel.carregarPosts()
    }

    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                SearchForum(query = textoBusca, onQueryChange = { textoBusca = it })
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    Log.d("ForumScreen", "Botão de criar post pressionado")
                    onToCreatePost()
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(BlueButton, CircleShape)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Criar Post", tint = Color.White)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (postsFiltrados.isEmpty() && !viewModel.isLoading!!) {
                item {
                    Text(
                        "Nenhum post encontrado.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                }
            }
            items(postsFiltrados) { post ->
                VerticalPostCard(
                    post = post,
                    viewModel = viewModel,
                    onClick = {
                        Log.d("ForumScreen", "Post pressionado: ${post.idPost}")
                        onNavigateToPostDetail(post.idPost)
                    }
                )
            }
        }
    }
}

@Composable
fun VerticalPostCard(
    post: Posts, // Usando seu modelo de dados real do Pots.kt
    viewModel: ForumViewModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title ?: "",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = post.content ?: "",
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            var isLiked by remember { mutableStateOf(false) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        Log.d("ForumScreen", "Toggle Like no post: ${post.idPost}")
                        isLiked = !isLiked // Muda o estado local
                        viewModel.toggleLike(post.idPost, !isLiked) // Chama a lógica do ViewModel
                    },
                    modifier = Modifier.size(20.dp) // Aumentei um pouco para facilitar o clique
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Default.ThumbUp else Icons.Default.ThumbUpOffAlt,
                        contentDescription = "Like",
                        // Se estiver curtido, fica Azul, se não, fica Cinza
                        tint = if (isLiked) BlueButton else Color.Gray
                    )
                }

                Text(
                    text = "${post.likesValue}",
                    modifier = Modifier.padding(start = 4.dp),
                    fontSize = 12.sp,
                    color = if (isLiked) BlueButton else Color.Gray
                )
                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    Icons.Default.Comment,
                    "",
                    tint = Color.Gray,
                    modifier = Modifier.size(14.dp)
                )
                Text("${post.comments?.size ?: 0} comentários", modifier = Modifier.padding(start = 4.dp), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SearchForum(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Buscar no fórum...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Close, contentDescription = "Limpar")
                }
            }
        },
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BlueButton,
            unfocusedBorderColor = Color(0xFFDDDDDD),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ForumScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            //ForumScreen()
        }
    }
}