package com.example.dalia2.ui.theme.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.dalia2.ui.theme.PinkButton
import com.example.dalia2.ui.theme.GrayButton

@Composable
fun QuizPeriod5Screen(
    onNextClick: (String) -> Unit = {} //Pode escolher mais de uma opção?
){
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        OptionButton(
            text = "Síndrome do ovário policístico",
            isSelected = selectedOption == "SOP",
            onClick = {
                selectedOption = "SOP"
                onNextClick("SOP")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OptionButton(
            text = "Endometriose",
            isSelected = selectedOption == "Endometriose",
            onClick = {
                selectedOption = "Endometriose"
                onNextClick("Endometriose")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OptionButton(
            text = "Tireoide desregulada",
            isSelected = selectedOption == "Tireoide",
            onClick = {
                selectedOption = "Tireoide"
                onNextClick("Tireoide")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OptionButton(
            text = "Outro",
            isSelected = selectedOption == "Outro",
            onClick = {
                selectedOption = "Outro"
                onNextClick("Outro")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OptionButton(
            text = "Nenhum",
            isSelected = selectedOption == "Nenhum",
            onClick = {
                selectedOption = "Nenhum"
                onNextClick("Nenhum")
            }
        )
    }
}

@Composable
fun OptionButton( //Função dos botões
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(304.dp)
            .height(44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) PinkButton else GrayButton,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
        )
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