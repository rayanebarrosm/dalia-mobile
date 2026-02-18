package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.GrayButton
import com.example.dalia2.ui.theme.PinkButton

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit = {},
    onSignupClick: () -> Unit = {}
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(width = 154.dp, height = 147.dp),
            contentScale = ContentScale.Fit

        )

        Button(
            onClick = onLoginClick,
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GrayButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Entrar", fontSize = 16.sp)
        }

        Button(
            onClick = onSignupClick,
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Criar conta", fontSize = 16.sp)
        }






    }

}