package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.PinkButton

@Composable
fun RegisterScreen(
    onNextClick: () -> Unit = {}
) {
    // Usando Surface para garantir que o fundo seja renderizado
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Registro Diário",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Por favor, selecione as opções com as quais você mais se identifica hoje",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Box com degradê para testar se as cores aparecem
            BoxDegrade(title = "Como está o seu humor?") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    EmojiItem(R.drawable.rg_happy)
                    EmojiItem(R.drawable.rg_neutral)
                    EmojiItem(R.drawable.rg_great)
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    EmojiItem(R.drawable.rg_sad)
                    EmojiItem(R.drawable.rg_sad)
                    EmojiItem(R.drawable.rg_fallinglove)
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    EmojiItem(R.drawable.rg_upset)
                    EmojiItem(R.drawable.rg_surprised)
                    EmojiItem(R.drawable.rg_grimace)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BoxDegrade(title = "Quais hábitos praticou?") {
                // Aqui você colocará seus botões de hábitos depois

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Leitura")
                    ButtonItem(text = "Meditação")
                    ButtonItem(text = "Terapia")
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Alongamento")
                    ButtonItem(text = "Pintura")
                    ButtonItem(text = "Banho de Sol")
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "DIY")
                    ButtonItem(text = "Esportes")
                    ButtonItem(text = "Outro")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BoxDegrade(title = "Quais sintomas sentiu?") {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Constipação")
                    ButtonItem(text = "Mudança de humor")
                    ButtonItem(text = "Nauseas")
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Dor de cabeça")
                    ButtonItem(text = "Fadiga")
                    ButtonItem(text = "Cólicas")
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Acne")
                    ButtonItem(text = "Inchaço")
                    ButtonItem(text = "Outro")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BoxDegrade(title = "Quais exercicios voce praticou?") {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Musculação")
                    ButtonItem(text = "Caminhada")
                    ButtonItem(text = "Aerobico")
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Corrida")
                    ButtonItem(text = "Dança")
                    ButtonItem(text = "Luta")
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Pilates")
                    ButtonItem(text = "?")
                    ButtonItem(text = "Outro")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BoxDegrade(title = "Vida íntima") {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Não tive relações")
                    ButtonItem(text = "Com proteção")
                    ButtonItem(text = "Sem proteção")
                    ButtonItem(text = "Masturbação")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BoxDegrade(title = "Secreção vaginal") {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Sem secreção")
                    ButtonItem(text = "Pastosa")
                    ButtonItem(text = "Aquoso")
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonItem(text = "Clara de ovo")
                    ButtonItem(text = "Corrimento")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {/*Salvar no banco?*/},
                modifier = Modifier.size(width = 304.dp, height = 44.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkButton),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Salvar", fontSize = 16.sp)
            }
        }
    }
}

//Função compose das sections
@Composable
fun BoxDegrade(
    title: String,
    content: @Composable ColumnScope.() -> Unit  //Passa o conteudo da interface dentro de Column
){
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFC7D0F2), Color(0xFFEFB2D9))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background( brush = gradient, shape = RoundedCornerShape(20.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            content()
        }
    }
}

@Composable
fun EmojiItem(iconRes: Int){
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            contentScale = androidx.compose.ui.layout.ContentScale.Fit
        )
    }
}

@Composable
fun ButtonItem(text: String) { // A função recebe o texto
    Button(
        onClick = { /* Ação */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(36.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
    ) {
        // O que o botão vai mostrar (o texto)
        Text(text = text, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}


@Preview(showBackground = true, heightDp = 2000)
@Composable
fun RegisterScreenPreview() {
    Dalia2Theme {
        RegisterScreen()
    }
}