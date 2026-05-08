package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.GrayButton
import com.example.dalia2.ui.theme.PinkButton
import com.example.dalia2.ui.theme.viewmodel.SearchViewModel

@Composable
fun QuizPeriod6Screen(
    viewModel: SearchViewModel,
    onNextClick: () -> Unit = {}
) {
    var textoDigitado by remember { mutableStateOf("") }
    // Estado para controlar se mostramos o erro visualmente
    var erroValidacao by remember { mutableStateOf(false) }
    LaunchedEffect(viewModel.searchSuccess) {
        if (viewModel.searchSuccess) {
            onNextClick()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {

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

            // Pergunta Principal
            Text(
                text = "Qual a duração média do seu ciclo?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "O intervalo deve ser entre 21 e 35 dias",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = textoDigitado,
                onValueChange = { novoTexto ->
                    // Aceita apenas números e até 2 dígitos
                    if (novoTexto.all { it.isDigit() } && novoTexto.length <= 2) {
                        textoDigitado = novoTexto
                        val valor = novoTexto.toIntOrNull()

                        if (valor != null) {
                            if (valor in 21..35) {
                                erroValidacao = false
                                viewModel.updateCicleDuration(valor)
                            } else {
                                erroValidacao = true
                            }
                        } else {
                            erroValidacao = false // Campo vazio não mostra erro imediatamente
                        }
                    }
                },
                label = { Text("Duração") },
                suffix = { Text("dias") }, // Fica bonitinho mostrar a unidade dentro do campo
                isError = erroValidacao, // Ativa a cor vermelha do Material Design
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Texto de erro que aparece embaixo do campo
            if (erroValidacao) {
                Text(
                    text = "A numeração não está no intervalo (21 a 35)",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.onSearchFinish() },
                // O botão só habilita se o valor for válido
                enabled = !erroValidacao && textoDigitado.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PinkButton,
                    disabledContainerColor = GrayButton
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Próximo", color = Color.White, fontSize = 16.sp)
            }
        }
        if (viewModel.isLoading) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = PinkButton)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizPeriod6ScreenPreview() {
    Dalia2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //QuizPeriod3Screen
        }
    }
}