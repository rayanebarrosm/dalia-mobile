package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.LightPink
import com.example.dalia2.ui.theme.Purple
import com.example.dalia2.ui.components.Destination
import com.example.dalia2.ui.theme.Black
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.example.dalia2.ui.theme.BlueButton
import com.example.dalia2.ui.theme.White
import kotlin.math.absoluteValue

data class MeuItem(
    val id: Int,
    val titulo: String,
    val dia: String,
    val isClickable: Boolean = true,
    val destination: String? = null

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToRegister: () -> Unit = {},
    onNavigateToCalendar: () -> Unit = {}
) {
    val meusDados = listOf(
        MeuItem(1, "Ontem", "Ontem", isClickable = true, destination = "calendar"),
        MeuItem(2, "Hoje", "Hoje", isClickable = true, destination = "register"),
        MeuItem(3, "Amanhã", "Amanhã", isClickable = false, destination = null)
    )

    val medicosDisponiveis = remember {
        listOf(
            MedicoData(1, "Dra. Ana Silva", "Ginecologista", "CRM 12345/SP"),
            MedicoData(2, "Dra. Beatriz Santos", "Obstetra", "CRM 67890/SP"),
            MedicoData(3, "Dr. Carlos Mendes", "Endocrinologista", "CRM 54321/RJ"),
            MedicoData(4, "Dra. Fernanda Lima", "Psicóloga Perinatal", "CRP 98765/SP")
        )
    }
    var selectedDay by remember { mutableStateOf(meusDados[1]) }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Seção dos dias
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            // Gradiente
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFF7CFC3),
                                Color(0xFFF7C4CF)
                            ),
                            radius = 600f,
                            center = Offset(0.5f, 0.5f)
                        )
                    )
            )

            // Carrossel de dias
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center //
            ) {
                DayCarousel(
                    itens = meusDados,
                    selectedDay = selectedDay,
                    onDaySelected = { day ->
                        //Rotas
                        if (day.isClickable) {
                            selectedDay = day
                            when (day.destination) {
                                "register" -> onNavigateToRegister()
                                "calendar" -> onNavigateToCalendar()
                            }
                        }
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Notícias sobre saúde",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Carrossel de notícias de saúde
            NewsCarousel(
                cardColor = Purple,
                newsType = "saude"
            )

            Spacer(modifier = Modifier.height(24.dp))

            //Banner
            Banner()

            Spacer(modifier = Modifier.height(24.dp))

            // Título: Notícias sobre legislação
            Text(
                text = "Notícias sobre legislação",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Carrossel de notícias de legislação
            NewsCarousel(
                cardColor = LightPink,
                newsType = "legislacao"
            )

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
}

//Dias de marcar o registro
@Composable
fun DayCarousel(
    itens: List<MeuItem>,
    selectedDay: MeuItem,
    onDaySelected: (MeuItem) -> Unit
) {
    val initialPageIndex = itens.indexOf(selectedDay).coerceAtLeast(0)

    val pagerState = rememberPagerState(
        initialPage = initialPageIndex,
        pageCount = { itens.size }
    )

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 90.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) { page ->
        val item = itens[page]
        val isSelected = pagerState.currentPage == page

        // Cálculo de escala e alpha para suavidade
        val pageOffset = (
                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val scale = lerp(0.8f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
        val alpha = lerp(0.6f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

        // Box centralizadora
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .width(150.dp)
                    .height(210.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .clickable(enabled = item.isClickable) { onDaySelected(item) },
                shape = CircleShape,
                color = if (isSelected) Color(0xFFFFF5E6) else Color.White.copy(alpha = 0.8f),
                shadowElevation = if (isSelected) 8.dp else 0.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.titulo.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = if (isSelected) Color.Black else Color.Gray
                    )

                    if (isSelected && item.titulo == "Hoje") {
                        Text(
                            text = "Como você está?",
                            fontSize = 12.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Banner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(BlueButton, LightPink)
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = "No Dália, nos importamos com a sua segurança, por isso gostaríamos que ficasse por dentro das leis que te defendem.",
                    fontSize = 14.sp,
                    color = Color.Black,
                    lineHeight = 20.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hm_moca),
                    contentDescription = "Moça demonstrando a força da mulher",
                    modifier = Modifier
                        .size(150.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

// Card de Notícia
@Composable
fun NewsCard(
    imageResId: Int,
    title: String,
    description: String,
    cardColor: Color,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Imagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFFFF)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Badge
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color.White
            ) {
                Text(
                    text = if (cardColor == Purple) "Saúde" else "Legislação",
                    fontSize = 10.sp,
                    color = cardColor,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                fontSize = 12.sp,
                color = Color(0xFF666666),
                maxLines = 2
            )
        }
    }
}

// Carrossel de Notícias
@Composable
fun NewsCarousel(
    cardColor: Color,
    newsType: String
) {
    data class NewsItem(
        val id: Int,
        val imageResId: Int,
        val title: String,
        val description: String
    )

    val newsItems = remember(newsType) {
        if (newsType == "saude") {
            listOf(
                NewsItem(0, R.drawable.lotus, "Como melhorar sua saúde mental", "Dicas simples para o dia a dia que podem transformar sua vida."),
                NewsItem(1, R.drawable.lotus, "Benefícios da atividade física", "Descubra como pequenos movimentos fazem diferença."),
                NewsItem(2, R.drawable.lotus, "Alimentação saudável na rotina", "Receitas fáceis e nutritivas para o seu dia."),
                NewsItem(3, R.drawable.lotus, "Sono reparador: guia completo", "Técnicas para melhorar sua qualidade de sono."),
                NewsItem(4, R.drawable.lotus, "Prevenção é o melhor caminho", "Exames e cuidados que você não pode ignorar.")
            )
        } else {
            listOf(
                NewsItem(0, R.drawable.lotus, "Nova lei de saúde pública", "Entenda como a nova legislação impacta seu dia a dia."),
                NewsItem(1, R.drawable.lotus, "Direitos das gestantes", "Conheça seus direitos e benefícios durante a gestação."),
                NewsItem(2, R.drawable.lotus, "Atualizações no SUS", "Saiba o que mudou no sistema público de saúde."),
                NewsItem(3, R.drawable.lotus, "Telemedicina: o que diz a lei", "Regulamentações e permissões para consultas remotas."),
                NewsItem(4, R.drawable.lotus, "Programas de saúde preventiva", "Legislação sobre campanhas de prevenção.")
            )
        }
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(newsItems) { item ->
            NewsCard(
                imageResId = item.imageResId,
                title = item.title,
                description = item.description,
                cardColor = cardColor,
                onClick = { /* Navegar para detalhes */ }
            )
        }
    }
}

// Componente do médico
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

// Navegação
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarExample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.Home.route
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                Destination.items.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon(),
                                contentDescription = destination.label
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        MainNavGraph(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(contentPadding)
        )
    }
}


@Composable
fun MainNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToCalendar = { navController.navigate(Destination.Calendar.route) }
            )
        }

        composable(Destination.Calendar.route) {
            CalendarScreen()
        }

        composable(Destination.Bot.route) {
            DaliaBotScreen()
        }

        composable(Destination.Forum.route) {
            ForumScreen()
        }

        composable(Destination.Settings.route) {
            ProfileScreen()
        }

        composable("register") {
            RegisterScreen()
        }
    }
}



@Preview(widthDp = 400,
    heightDp = 1800)
@Composable
fun HomeScreenPreview() {
    Dalia2Theme {
        HomeScreen()
    }
};