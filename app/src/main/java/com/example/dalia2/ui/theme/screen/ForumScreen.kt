package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.BlueButton
import com.example.dalia2.ui.theme.White
import com.example.dalia2.ui.theme.Black

data class PostData(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val descricao: String = "",
    val curtidas: Int = 0,
    val comentarios: Int = 0
)

data class MedicoData(
    val id: Int,
    val nome: String,
    val especialidade: String,
    val crm: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen() {
    val categorias = listOf("Geral", "Saúde física", "Saúde mental", "Menstruação", "Gestação")
    var categoriaSelecionada by remember { mutableStateOf("Geral") }
    var textoBusca by remember { mutableStateOf("") }

    var todosOsPosts by remember {
        mutableStateOf(
            listOf(
                PostData(0, "Importância do sono para gestantes", "Saúde física", "Descubra como uma boa noite de sono pode beneficiar você e seu bebê.", 0, 0),
                PostData(1, "Como lidar com a ansiedade na gestação", "Saúde mental", "Técnicas simples para controlar a ansiedade durante a gravidez.", 0, 0),
                PostData(2, "Dúvidas comuns sobre o ciclo menstrual", "Menstruação", "Esclareça as principais dúvidas sobre o ciclo menstrual.", 0, 0),
                PostData(3, "Dicas de alimentação saudável na gestação", "Gestação", "Alimentos que não podem faltar na sua dieta durante a gestação.", 0, 0),
                PostData(4, "Exercícios para aliviar cólicas menstruais", "Menstruação", "Movimentos simples que ajudam a reduzir as cólicas.", 0, 0),
                PostData(5, "Quando procurar um médico?", "Geral", "Sinais de alerta que indicam a necessidade de ajuda profissional.", 0, 0)
            )
        )
    }

    // Função para adicionar curtida
    fun adicionarCurtida(postId: Int) {
        todosOsPosts = todosOsPosts.map { post ->
            if (post.id == postId) {
                post.copy(curtidas = post.curtidas + 1)
            } else {
                post
            }
        }
    }

    // Função para adicionar comentário
    fun adicionarComentario(postId: Int) {
        todosOsPosts = todosOsPosts.map { post ->
            if (post.id == postId) {
                post.copy(comentarios = post.comentarios + 1)
            } else {
                post
            }
        }
    }

    val postMaisFamoso = todosOsPosts.maxByOrNull { it.curtidas } ?: todosOsPosts.first()

    val postsFiltrados = todosOsPosts.filter { post ->
        val combinaCategoria = (categoriaSelecionada == "Geral" || post.categoria == categoriaSelecionada)
        val combinaTexto = post.titulo.contains(textoBusca, ignoreCase = true) ||
                post.descricao.contains(textoBusca, ignoreCase = true) ||
                textoBusca.isEmpty()
        combinaCategoria && combinaTexto
    }

    val medicosDisponiveis = remember {
        listOf(
            MedicoData(1, "Dra. Ana Silva", "Ginecologista", "CRM 12345/SP"),
            MedicoData(2, "Dra. Beatriz Santos", "Obstetra", "CRM 67890/SP"),
            MedicoData(3, "Dr. Carlos Mendes", "Endocrinologista", "CRM 54321/RJ"),
            MedicoData(4, "Dra. Fernanda Lima", "Psicóloga Perinatal", "CRP 98765/SP")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        SearchForum(
            query = textoBusca,
            onQueryChange = { textoBusca = it }
        )

        Spacer(modifier = Modifier.height(16.dp))
        FeaturedPostCard(post = postMaisFamoso)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Categorias",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(categorias) { categoria ->
                val isSelected = categoria == categoriaSelecionada
                Surface(
                    modifier = Modifier
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { categoriaSelecionada = categoria },
                    color = if (isSelected) BlueButton else Color.White,
                    shadowElevation = if (isSelected) 0.dp else 1.dp,
                    tonalElevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = categoria,
                            color = if (isSelected) White else Color(0xFF555555),
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Matérias para você",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (postsFiltrados.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Text(
                    text = "Nenhuma matéria encontrada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        } else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(postsFiltrados) { post ->
                    PostCard(
                        post = post,
                        onCurtir = { adicionarCurtida(post.id) },
                        onComentar = { adicionarComentario(post.id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Médicos disponíveis",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Black
        )
        Text(
            text = "Agende uma consulta com nossos especialistas",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            fontSize = 12.sp,
            color = Color(0xFF888888)
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(medicosDisponiveis) { medico ->
                DoctorCardHorizontal(medico = medico)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun PostCard(
    post: PostData,
    onCurtir: () -> Unit = {},
    onComentar: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .clickable { /* Navegar para detalhes do post */ },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BlueButton.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                val icon = when (post.categoria) {
                    "Saúde física" -> Icons.Default.FitnessCenter
                    "Saúde mental" -> Icons.Default.Psychology
                    "Menstruação" -> Icons.Default.WaterDrop
                    "Gestação" -> Icons.Default.PregnantWoman
                    else -> Icons.Default.Article
                }
                Icon(
                    icon,
                    contentDescription = null,
                    tint = BlueButton,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                shape = RoundedCornerShape(4.dp),
                color = BlueButton.copy(alpha = 0.12f)
            ) {
                Text(
                    text = post.categoria,
                    fontSize = 10.sp,
                    color = BlueButton,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = post.titulo,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = post.descricao,
                fontSize = 12.sp,
                color = Color(0xFF666666),
                maxLines = 2,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onCurtir() }
                ) {
                    Icon(
                        Icons.Default.ThumbUp,
                        contentDescription = null,
                        tint = Color(0xFF999999),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.curtidas}", fontSize = 11.sp, color = Color(0xFF999999))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onComentar() }
                ) {
                    Icon(
                        Icons.Default.Comment,
                        contentDescription = null,
                        tint = Color(0xFF999999),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.comentarios}", fontSize = 11.sp, color = Color(0xFF999999))
                }
            }
        }
    }
}

// Componente do médico (mantido igual)
@Composable
fun DoctorCardHorizontal(medico: MedicoData) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .clickable { /* Agendar consulta */ },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(BlueButton.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = BlueButton,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = medico.nome,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = medico.especialidade,
                fontSize = 13.sp,
                color = BlueButton,
                textAlign = TextAlign.Center
            )

            Text(
                text = medico.crm,
                fontSize = 11.sp,
                color = Color(0xFF888888),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { /* Agendar consulta */ },
                colors = ButtonDefaults.buttonColors(containerColor = BlueButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agendar", fontSize = 13.sp, color = White)
            }
        }
    }
}

@Composable
fun FeaturedPostCard(post: PostData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFECE0F4))
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "⭐ DESTAQUE",
                fontSize = 10.sp,
                color = Color.Black.copy(alpha = 0.6f),
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = post.titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.ThumbUp,
                        contentDescription = null,
                        tint = Color(0xFF6B3FA0),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.curtidas}", fontSize = 12.sp, color = Color(0xFF6B3FA0))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Comment,
                        contentDescription = null,
                        tint = Color(0xFF6B3FA0),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.comentarios}", fontSize = 12.sp, color = Color(0xFF6B3FA0))
                }
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

// EXEMPLO DE PÁGINA DE POST (adicione isso ao seu projeto)
@Composable
fun PostDetailScreen(postId: Int, onBackClick: () -> Unit) {
    // Aqui você buscaria o post pelo ID
    // Por enquanto um exemplo estático
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
        }

        Text(
            text = "Título do Post",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Conteúdo do post aqui...",
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForumScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            ForumScreen()
        }
    }
}