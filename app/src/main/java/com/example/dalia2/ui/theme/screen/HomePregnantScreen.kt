package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Black
import com.example.dalia2.ui.theme.BlueButton
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.LightPink
import kotlin.math.absoluteValue

// Data class
data class MeuItemPregnant(
    val id: Int,
    val titulo: String,
    val dia: String,
    val isClickable: Boolean = true,
    val destination: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePregnantScreen(
    onNavigateToRegister: () -> Unit = {},
    onNavigateToCalendar: () -> Unit = {}
) {
    val meusDados = listOf(
        MeuItemPregnant(1, "Ontem", "Ontem", isClickable = true, destination = "calendar"),
        MeuItemPregnant(2, "Hoje", "Hoje", isClickable = true, destination = "register"),
        MeuItemPregnant(3, "Amanhã", "Amanhã", isClickable = false, destination = null)
    )

    var selectedDay by remember { mutableStateOf(meusDados[1]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Seção dos dias com gradiente
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
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

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                DayCarouselPregnant(
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

            Text(
                text = "Notícias sobre sua saúde e do seu bebê:",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botões de filtro
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* Filtrar vacinação */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8CA3)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Vacinação", fontSize = 12.sp, color = Color.White)
                }
                Button(
                    onClick = { /* Filtrar corpo */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8CA3)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Corpo", fontSize = 12.sp, color = Color.White)
                }
                Button(
                    onClick = { /* Filtrar alimentação */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8CA3)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Alimentação", fontSize = 12.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Carrossel de notícias
            NewsCarouselPregnant(
                cardColor = BlueButton,
                newsType = "gestante",
                imageResId = R.drawable.lotus
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Banner
            Banner()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Notícias sobre legislação",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Carrossel de notícias - SEGUNDA FILEIRA (Cor FF8E8E)
            NewsCarouselPregnant(
                cardColor = Color(0xFFFF8E8E),
                newsType = "legislacao",
                imageResId = R.drawable.lotus
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun BannerPregnant() {
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
                    text = "Acompanhamento gestacional é essencial! Cuide de você e do seu bebê com nossas dicas diárias.",
                    fontSize = 14.sp,
                    color = Color.White,
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
                    contentDescription = "Gestante",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun DayCarouselPregnant(
    itens: List<MeuItemPregnant>,
    selectedDay: MeuItemPregnant,
    onDaySelected: (MeuItemPregnant) -> Unit
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
        val pageOffset = (
                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                ).absoluteValue

        val scale = lerp(0.8f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
        val alpha = lerp(0.6f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

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
fun NewsCardPregnant(
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
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

            Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color.White
            ) {
                Text(
                    text = if (cardColor == BlueButton) "Gestante" else "Legislação",
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

@Composable
fun NewsCarouselPregnant(
    cardColor: Color,
    newsType: String,
    imageResId: Int
) {
    data class NewsItem(
        val id: Int,
        val title: String,
        val description: String
    )

    val newsItems = remember(newsType) {
        if (newsType == "gestante") {
            listOf(
                NewsItem(0, "Cuidados na gestação: primeiro trimestre", "Tudo que você precisa saber sobre os primeiros meses."),
                NewsItem(1, "Alimentação saudável para gestantes", "Nutrientes essenciais para você e seu bebê."),
                NewsItem(2, "Exercícios permitidos na gravidez", "Movimentos seguros para manter a saúde."),
                NewsItem(3, "Preparação para o parto", "Dicas para se preparar para este momento especial."),
                NewsItem(4, "Amamentação: primeiros passos", "Guia para iniciar a amamentação com sucesso.")
            )
        } else {
            listOf(
                NewsItem(0, "Nova lei de saúde pública", "Entenda como a nova legislação impacta seu dia a dia."),
                NewsItem(1, "Direitos das gestantes", "Conheça seus direitos e benefícios durante a gestação."),
                NewsItem(2, "Atualizações no SUS", "Saiba o que mudou no sistema público de saúde."),
                NewsItem(3, "Telemedicina: o que diz a lei", "Regulamentações e permissões para consultas remotas."),
                NewsItem(4, "Programas de saúde preventiva", "Legislação sobre campanhas de prevenção.")
            )
        }
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(newsItems) { item ->
            NewsCardPregnant(
                imageResId = imageResId,
                title = item.title,
                description = item.description,
                cardColor = cardColor,
                onClick = { /* Navegar para detalhes */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePregnantScreenPreview() {
    Dalia2Theme {
        HomePregnantScreen()
    }
}