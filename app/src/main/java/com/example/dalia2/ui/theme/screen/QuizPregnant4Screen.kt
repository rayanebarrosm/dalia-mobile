package com.example.dalia2.ui.theme.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme

@Composable
fun QuizPregnant4Screen(
    onNextClick: (Int, Int) -> Unit = { _, _ -> } // Recebe Int em vez de String
) {

    var month by remember { mutableStateOf("") }
    var weeks by remember { mutableStateOf("") }
    var monthError by remember { mutableStateOf<String?>(null) }
    var weeksError by remember { mutableStateOf<String?>(null) }

    fun validateInputs(): Boolean{
        val monthInt = month.toIntOrNull()
        monthError = when{
            month.isEmpty() -> "Mês é obrigatório"
            monthInt !in 1..9 -> "Mês deve ser entre 1 e 9"
            else -> null
        }

        val weeksInt = weeks.toIntOrNull()
        weeksError = when {
            weeks.isEmpty() -> "Semana é obrigatório"
            weeksInt !in 1..40 -> "Semana deve ser entre 1 e 40"
            else -> null
        }

        return monthError == null && weeksError == null
    }


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
            text = "Tempo gestacional",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Coloque aqui a data do parto",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedTextField(
            value = month,
            onValueChange = {
                if (it.length <= 2 && (it.isEmpty() || it.all { char -> char.isDigit() })) {
                    month = it
                }
            },
            label = { Text("Mês") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //Abre teclado numérico
            singleLine = true
        )

        OutlinedTextField(
            value = weeks,
            onValueChange = {
                if (it.length <= 2 && (it.isEmpty() || it.all { char -> char.isDigit() })) {
                    weeks = it
                }
            },
            label = { Text("Semana (0-40)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Button(
            onClick = {
                if (validateInputs()) {
                    val monthInt = month.toInt()
                    val weeksInt = weeks.toInt()
                    onNextClick(monthInt, weeksInt)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.button_nextpage),
                contentDescription = "Próximo",
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun QuizPregnant4ScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            QuizPregnant4Screen()
        }
    }
}