package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.PinkButton
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun QuizPeriod4Screen(
    onNextClick: () -> Unit = {}
) {

    //Variaveis de datas
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) } // Visualização de até 1 ano atrás

    //armazena data selecionada
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }


    //Gerenciador a visualização de datas
    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = currentMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = DayOfWeek.SUNDAY //Faz com que o domingo seja o primiro dia da semana
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(154.dp, 147.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Marque o dia da sua ultima menstruação",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(25.dp))

        HorizontalCalendar(
            state = calendarState,
            dayContent = { day ->
                Day(
                    day = day, // dia atual
                    isSelected = selectedDate == day.date, //Confirma a data?? booleano
                    onClick = {
                        selectedDate = day.date //adiciona o dia selecionado a selectedDate/Atualiza o estado
                    }
                )
            },

            modifier = Modifier.weight(1f) //tamanho do calendario

        )

        //Quando a data for selecionada o botão aparece
        if (selectedDate != null) {
            Button(
                onClick = {
                    saveSelectedDate(selectedDate!!)
                    onNextClick
                }, //bota as rotas, o app navigation define e o onclick usa
                modifier = Modifier
                    .width(304.dp)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
                shape = RoundedCornerShape(8.dp)

            ) {
                Text("Continuar", fontSize = 16.sp)
            }
        }

    }
}

@Composable
fun Day(
    day: CalendarDay,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Box( //Ambos mudam a cor da data quando selecionada
        modifier = Modifier
            .size(30.dp)
            .background(color = if(isSelected) PinkButton else MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick),
    )
    Text(
        text = day.date.dayOfMonth.toString(), //pega o texto do calendario(sem isso não funciona)
        fontSize = 14.sp,
        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground
    )


}

//Função para salvar a data ou colocar no viewModel?????
fun saveSelectedDate(date: LocalDate){
    println("Data salva: $date")
}

@Preview(showBackground = true)
@Composable
fun QuizPeriod4ScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            QuizPeriod4Screen()
        }
    }
}