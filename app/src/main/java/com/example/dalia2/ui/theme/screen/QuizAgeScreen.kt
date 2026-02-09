package com.example.dalia2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme

@Composable
fun QuizAgeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // O ImageView com bias 0.186 no XML é simulado com um Spacer flexível ou fixo
        Spacer(modifier = Modifier.height(80.dp))

        // 1. Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(154.dp, 147.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 2. Título
        Text(
            text = "Qual modo você gostaria de usar?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp, // Ajuste para o texto não ficar muito "apertado"
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp) // Espaço fixo entre os botões
        ) {
            Button(
                text = "Menor de 18 anos",
            )

            Button(
                text = "18 a 30",
            )

            Button(
                text = "30 a 45",
            )

            Button(
                text = "maior de 45 anos",
            )
        }

        Button(

        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizAgeScreenPreview() {
    Dalia2Theme {
        QuizAgeScreen()
    }
}