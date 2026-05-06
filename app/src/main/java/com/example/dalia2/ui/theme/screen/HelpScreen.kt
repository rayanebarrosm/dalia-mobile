package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Black
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.PinkButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Suporte do Dália",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.hp_pgvazia),
                contentDescription = "Imagem ilustrativa",
                modifier = Modifier.size(140.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nenhuma dúvida foi registrada",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(45.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Ainda precisa de ajuda?",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /* email suporte */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.hp_contact),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = PinkButton
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Contate o suporte",
                    color = PinkButton,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Versão: 3.0.0",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { /* pagina do termo */ },
                modifier = Modifier.fillMaxWidth() )
            {
                Text(
                    text = "Termos de uso",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Política de Privacidad",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black
            )
        }
    }
}

@Preview
@Composable
fun HelpScreenPreview() {
    Dalia2Theme {
        HelpScreen()
    }
}