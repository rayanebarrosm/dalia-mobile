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
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.LightPink
import com.example.dalia2.ui.theme.Purple
import com.example.dalia2.ui.components.Destination
import com.example.dalia2.ui.theme.Black

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

    var selectedDay by remember { mutableStateOf(meusDados[1]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // ✅ FUNDO BRANCO na tela toda
    ) {
        // Seção dos dias com gradiente
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp) // Altura aumentada para centralizar
        ) {
            // Gradiente com as cores solicitadas
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFF7CFC3), // Centro
                                Color(0xFFF7C4CF)  // Pontas
                            ),
                            radius = 600f,
                            center = Offset(0.5f, 0.5f)
                        )
                    )
            )

            // Carrossel de dias - CENTRALIZADO VERTICALMENTE
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center // ✅ Centraliza verticalmente
            ) {
                DayCarousel(
                    itens = meusDados,
                    selectedDay = selectedDay,
                    onDaySelected = { day ->
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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Título: Notícias sobre saúde (COR PURPLE)
            Text(
                text = "Notícias sobre saúde",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black, // ✅ Cor Purple
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Carrossel de notícias de saúde (COR PURPLE)
            NewsCarousel(
                cardColor = Purple,
                newsType = "saude"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Título: Notícias sobre legislação (COR PINKLIGHT)
            Text(
                text = "Notícias sobre legislação",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black, // ✅ Cor PinkLight
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Carrossel de notícias de legislação (COR PINKLIGHT)
            NewsCarousel(
                cardColor = LightPink,
                newsType = "legislacao"
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// COMPONENTE: Carrossel de Dias (formato OVAL - mais largo que alto)
@Composable
fun DayCarousel(
    itens: List<MeuItem>,
    selectedDay: MeuItem,
    onDaySelected: (MeuItem) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { itens.size }
    )

    LaunchedEffect(Unit) {
        pagerState.scrollToPage(1)
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 80.dp),
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val item = itens[page]
        val isSelected = pagerState.currentPage == page
        val isClickable = item.isClickable

        // ✅ FORMATO OVAL (largura maior que altura)
        Surface(
            modifier = Modifier
                .width(if (isSelected) 120.dp else 90.dp)   // Largura
                .height(if (isSelected) 160.dp else 110.dp) // Altura maior (oval em pé)
                .scale(if (isSelected) 1.0f else 0.85f)
                .then(
                    if (isClickable) Modifier.clickable { onDaySelected(item) } else Modifier
                ),
            shape = CircleShape,
            color = if (isSelected) Color(0xFFFF8CA3) else Color.White.copy(alpha = 0.9f),
            shadowElevation = if (isSelected) 8.dp else 2.dp,
            tonalElevation = 0.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.titulo,
                        fontSize = if (isSelected) 18.sp else 14.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else Color(0xFF666666),
                        textAlign = TextAlign.Center
                    )

                    if (!isClickable) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "🔒",
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

// COMPONENTE: Card de Notícia
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

            // Badge com a cor correspondente
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

// COMPONENTE: Carrossel de Notícias
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Dalia2Theme {
        HomeScreen()
    }
}