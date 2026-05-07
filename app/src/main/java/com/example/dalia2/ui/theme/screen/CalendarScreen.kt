package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dalia2.ui.theme.viewmodel.CalendarViewModel
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import com.example.dalia2.ui.theme.Bege

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel()
) {/*
    // 1. Pegamos os dados do "Cérebro" (ViewModel)
    val menstruacao by viewModel.diasMenstruacao.collectAsState()
    val fertil by viewModel.diasFertil.collectAsState()

    // 2. Configuramos o estado do calendário para o ano atual
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) } // 1 ano para trás
    val endMonth = remember { currentMonth.plusMonths(12) }   // 1 ano para frente
    val selections = remember { mutableStateListOf<CalendarDay>() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeekFromLocale()
    )


Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB7DAD8),
                        Color(0xFFBED7ED)
                    )
                )
            )
    ) {
    // Calendário que rola na Vertical
    VerticalCalendar(
        state = state,
        dayContent = { day ->
            // Aqui acontece a "mágica" de pintar os dias
            Day(
                day = day,
                isMenstruacao = menstruacao.contains(day.date),
                isFertil = fertil.contains(day.date)
            )
        },
        monthHeader = { month ->
            // Nome do mês (Janeiro, Fevereiro...)
            Text(
                text = month.yearMonth.month.name,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    )

    Button(
        onClick = {
            onNextClick(),
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Bege),
            shape = RoundedCornerShape(8.dp)
        }
    ){
            Text("Preencher o registro diário", fontSize = 16.sp)
        }


}
@Composable
fun Day(day: CalendarDay, isMenstruacao: Boolean, isFertil: Boolean) {
    Box(
        modifier = Modifier
            .aspectRatio(1f) // Deixa o dia quadradinho
            .padding(2.dp)
            .background(
                color = when {
                    isMenstruacao -> Color(0xFFFFD1DC) // Rosa Dalia
                    isFertil -> Color(0xFFE0F7FA)      // Azul Bebê
                    else -> Color.Transparent
                },
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate) Color.Black else Color.Gray
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
*/
}