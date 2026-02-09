package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.screen.QuizModeScreen
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.GrayButton
import com.example.dalia2.ui.theme.PinkButton

@Composable
fun QuizPeriod5Screen(){
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
            text = "Você sofre de algum desses fatores?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {  },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sindrome do ovário policístico", fontSize = 16.sp)
        }

        Button(
            onClick = { },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Endometriose", fontSize = 16.sp)
        }

        Button(
            onClick = { },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Tireoide desregulada", fontSize = 16.sp)
        }

        Button(
            onClick = { },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Outro", fontSize = 16.sp)
        }

        Button(
            onClick = { },
            modifier = Modifier.size(width = 304.dp, height = 44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Nenhum", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizPeriod5ScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            QuizPeriod5Screen()
        }
    }
}