package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.viewmodel.SearchViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizPeriod4Screen(
    viewModel: SearchViewModel,
    onNextClick: () -> Unit = {}
) {
    //Variaveis de datas
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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

        OutlinedButton(onClick = { showDatePicker = true }) {
            Icon(Icons.Default.CalendarToday, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(text = datePickerState.selectedDateMillis?.let {
               formatarData(it)
            } ?: "Selecionar data")
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val selectedDate = formatarData(datePickerState.selectedDateMillis)
                        viewModel.updateMenstraucaoDia(selectedDate)
                        showDatePicker = false
                        onNextClick()
                    }) { Text("Confirmar") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

fun formatarData(millis: Long?): String {
    if (millis == null) return ""
    val data = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    // Formato padrão para APIs: AAAA-MM-DD
    return data.format(DateTimeFormatter.ISO_LOCAL_DATE)
}

@Preview(showBackground = true)
@Composable
fun QuizPeriod4ScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //QuizPeriod4Screen{}
        }
    }
}