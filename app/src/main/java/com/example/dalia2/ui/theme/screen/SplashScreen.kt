package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dalia2.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(true) {
        delay(5000)
        navController.navigate()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(color = ),
        verticalArrangement = Arrengement.Center
        horizontalAligment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(160.dp, 160.dp),

        )

        Text(
            text = "Dália",
            fontSize = 22.sp
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview(){
 SplashScreen(navController = rememberNavController())
}
