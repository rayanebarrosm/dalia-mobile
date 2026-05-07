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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.GrayButton
import com.example.dalia2.ui.theme.PinkButton
import com.example.dalia2.ui.theme.Purple
import com.example.dalia2.ui.theme.White
import com.example.dalia2.ui.theme.viewmodel.SearchViewModel
import java.net.URLEncoder

@Composable
fun QuizAgeScreen(
    viewModel: SearchViewModel,
    onNextClick: () -> Unit //Passa a idade
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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

        Button(
            onClick = {
                viewModel.updateAge(17)
                onNextClick() },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrayButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Menor de 18 anos", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                viewModel.updateAge(25)
                onNextClick() },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrayButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("18 a 30", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                viewModel.updateAge(35)
                onNextClick() },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrayButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("31 a 45 anos", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                viewModel.updateAge(45)
                onNextClick() },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrayButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Mais de 45 anos", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizAgeScreenPreview() {
    Dalia2Theme {
        //QuizAgeScreen{

    }
}