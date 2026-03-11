package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.draw.*
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.components.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Altura fixa para a seção dos dias
        ) {
            // Imagem de fundo apenas para esta seção
            Image(
                painter = painterResource(id = R.drawable.gradient_home),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )



        val meusDados = listOf(
            MeuItem(1, "Hoje", Color.White),
            MeuItem(2, "Amanhã", Color.White),
            MeuItem(3, "Depois", Color.White)
        )

        CarouselList(itens = meusDados)
    }
        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Spacer(modifier = Modifier.height(24.dp))

         Text(
                text = "Noticias sobre saude",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
         )

         CarouselCards()

         Text(
                text = "Noticias sobre legislação",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
         )

         CarouselCards()

        }
    }
}

data class MeuItem(val id: Int, val titulo: String, val cor: Color)

@Composable
fun CarouselList(itens: List<MeuItem>) {
    val pagerState = rememberPagerState(pageCount = { itens.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 64.dp)
    ) { page ->
        val item = itens[page] // Pegamos o item baseado na posição
        val isSelected = pagerState.currentPage == page

        // ... (resto da lógica de escala igual à anterior)
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .scale(if (isSelected) 1.2f else 0.8f)
                .background(item.cor) // Cor dinâmica!
        ) {
            Text(item.titulo, color = Color.White)
        }
    }
}



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
                                destination.icon(),  // ⬅️ Chama a função!
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
fun Card(
    imageRes: Int,  // Adicionado parâmetro para imagem
    text: String,    // Adicionado parâmetro para texto
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.size(width = 150.dp, height = 167.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary, // Corrigido: purple não existe, use primary
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}

//Carrosel de noticias
@Composable
fun CarouselCards() {
    data class CarouselItem(
        val id: Int,
        val imageResId: Int,
        val text: String  // Adicionado campo para texto
    )

    val items = remember {
        listOf(
            CarouselItem(0, R.drawable.lotus, "Noticia 1"),
            CarouselItem(1, R.drawable.lotus, "Noticia 2"),
            CarouselItem(2, R.drawable.lotus, "Noticia 3"),
            CarouselItem(3, R.drawable.lotus, "Noticia 4"),
            CarouselItem(4, R.drawable.lotus, "Noticia 5"),
        )
    }

    // Usando LazyRow como alternativa mais simples ao HorizontalMultiBrowseCarousel
    androidx.compose.foundation.lazy.LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items.size) { index ->
            val item = items[index]
            Card(
                imageRes = item.imageResId,
                text = item.text
            )
        }
    }
}


//Navegaçõs pelos botões
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
            HomeScreen()
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
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Dalia2Theme {
        HomeScreen()
    }
}

