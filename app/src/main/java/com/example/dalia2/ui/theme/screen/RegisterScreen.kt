package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.PinkButton

@Composable
fun QuizRegisterScreen (
    onNextClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Registro Diário",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Por favor, selecione as opções com as quais você mais se identifica hoje",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))


        Box(
            modifier = Modifier
                .aspectRatio(1f) // Quadrado perfeito baseado na largura da coluna
                .padding(2.dp)   // Pequeno espaço entre os dias
                .background(
                    shape = RoundedCornerShape(8.dp) // Ou CircleShape para ficar redondo
        ) {

                    Image(
                        painter = painterResource(id = R.drawable.rg_happy),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        painter = painterResource(id = R.drawable.rg_great),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        painter = painterResource(id = R.drawable.rg_neutral),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        painter = painterResource(id = R.drawable.rg_grimace),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        painter = painterResource(id = R.drawable.rg_upset),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        painter = painterResource(id = R.drawable.rg_sad),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        painter = painterResource(id = R.drawable.rg_surprised),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        painter = painterResource(id = R.drawable.rg_fallinglove),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(154.dp, 147.dp),
                        contentScale = ContentScale.Fit
                    )

         }

        Box(
              modifier = Modifier
                .aspectRatio(1f) // Quadrado perfeito baseado na largura da coluna
                .padding(2.dp)   // Pequeno espaço entre os dias
                .background(
                    shape = RoundedCornerShape(8.dp) // Ou CircleShape para ficar redondo
        ) {



        }

        Box()

        Box()

        Box()

        Button() {

        }
    }
}