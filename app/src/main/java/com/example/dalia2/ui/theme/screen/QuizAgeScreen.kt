package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.*
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.PinkButton
import com.example.dalia2.ui.theme.purple
import com.example.dalia2.ui.theme.White

@Composable
fun QuizAgeScreen(
    onNextClick: (String) -> Unit = {} //Passa a idade
){

    var selectedAge by remember { mutableStateOf<String?>(null) }

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
            text = "Qual a sua faixa etária?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp) // Espaço fixo entre os botões
        ) {
            AgeButton(
                text = "Menor de 18 anos",
                isSelected = selectedAge == "Menor de 18 anos",
                onClick = { selectedAge = "Menor de 18 anos" }
            )

            AgeButton(
                text = "18 a 30 anos",
                isSelected = selectedAge == "18 a 30 anos",
                onClick = { selectedAge = "18 a 30 anos" }
            )

            AgeButton(
                text = "31 a 45 anos",
                isSelected = selectedAge == "31 a 45 anos",
                onClick = { selectedAge = "31 a 45 anos" }
            )

            AgeButton(
                text = "Maior de 45 anos",
                isSelected = selectedAge == "Maior de 45 anos",
                onClick = { selectedAge = "Maior de 45 anos" }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                selectedAge?.let { age ->
                    onNextClick(age)  // Passa a idade selecionada
                }
            },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkButton,  contentColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            enabled = selectedAge != null
        ) {
            Text("Selecionar", fontSize = 16.sp)
        }
    }
}

@Composable
fun AgeButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(304.dp)
            .height(44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) purple else White,
            contentColor = if(isSelected) Color.White else Color.Black
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
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