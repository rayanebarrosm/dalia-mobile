package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.ui.theme.PinkButton
import com.example.dalia2.ui.theme.BlueButton
import com.example.dalia2.ui.theme.Dalia2Theme

// --- FUNÇÕES DE APOIO (MOCKS) ---
// Simulam a busca de dados para que o código compile sem erros
fun searchPostById(id: Int): String {
    return "Conteúdo do post"
}

fun buscarComentariosDoPost(id: Int): List<String> {
    return listOf("Excelente explicação!", "Doutor, tenho uma dúvida sobre isso.")
}

@Composable
fun PostDetailScreen(postId: Int) {
    val postConteudo = remember { searchPostById(postId) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            item {
                // Card que envolve Cabeçalho Azul + Conteúdo Branco
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        // --- 1. CABEÇALHO (Fundo Azul) ---
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = BlueButton,
                            contentColor = Color.White
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Foto de Perfil
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(Color.White.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        Icons.Default.Person,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                // Informações do Autor
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Título do Post",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            fontSize = 18.sp
                                        )
                                        Text(
                                            text = "14:30",
                                            color = Color.Black,
                                            fontSize = 12.sp
                                        )
                                    }
                                    Text(
                                        text = "Dr. Carlos Mendes",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "Endocrinologista",
                                        color = Color.Black,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }

                        // --- 2. CONTEÚDO (Fundo Branco) ---
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White,
                            shape = RectangleShape
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = postConteudo,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Comentários",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 2. Lista de Comentários
            items(5) { index ->
                CommentItem(
                    autor = "Usuário Exemplo $index",
                    texto = "Comentário"
                )
                HorizontalDivider(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        // 3. Barra Fixa para Comentar
        CommentInputField()
    }
}

@Composable
fun CommentInputField() {
    var textState by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                placeholder = { Text("Deixe um comentário...") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(

                    focusedIndicatorColor = PinkButton,
                    cursorColor = PinkButton
                )
            )
            IconButton(onClick = { textState = "" }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Enviar",
                    tint = BlueButton
                )
            }
        }
    }
}

@Composable
fun CommentItem(autor: String, texto: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = autor,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = BlueButton
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = texto, fontSize = 14.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailPreview() {
    Dalia2Theme {
        // Simula a visualização com um ID qualquer
        PostDetailScreen(postId = 1)
    }
}