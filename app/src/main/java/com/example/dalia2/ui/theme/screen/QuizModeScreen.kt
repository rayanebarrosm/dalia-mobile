package com.example.dalia2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.GrayButton

@Composable
fun QuizModeScreen(
    onPregnantModeClick: () -> Unit = {},
    onPeriodModeClick: () -> Unit = {},
    onBackClick: () -> Unit = {} // Opcional: para voltar
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. LOGO - Posicionada com bias 0.186 (18.6% do topo)
        Spacer(modifier = Modifier.height(80.dp)) // Aproximação do vertical_bias

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo do app",
            modifier = Modifier.size(154.dp, 147.dp),
            contentScale = ContentScale.Fit
        )

        // 2. TÍTULO - TextView centralizado
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Qual modo você gostaria de usar?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold, // fontFamily="@font/inter_semibold"
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // 3. BOTÕES - LinearLayout vertical
        Spacer(modifier = Modifier.height(20.dp))

        // Botão Modo Gravidez
        Button(
            onClick = onPregnantModeClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GrayButton
            )
        ) {
            Text(
                text = "Modo Gravidez",
                fontSize = 16.sp
            )
        }

        // Espaçamento entre botões (layout_marginTop="24dp")
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onPeriodModeClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GrayButton
            )
        ) {
            Text(
                text = "Menstruação",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun QuizModeScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            QuizModeScreen()
        }
    }
}