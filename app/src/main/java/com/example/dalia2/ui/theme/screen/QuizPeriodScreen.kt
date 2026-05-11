package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.GrayButton
import com.example.dalia2.ui.theme.PinkButton
import com.example.dalia2.ui.theme.viewmodel.QuizViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


enum class TipoPergunta { BOTAO, DATA, NUMERO }

data class Pergunta(
    val titulo: String,
    val tipo: TipoPergunta,
    val campo: String,
    val opcoes: List<Pair<String, Any>> = emptyList()
)

@Composable
fun QuizPeriodScreen(
    viewModel: QuizViewModel,
    onQuizComplete: () -> Unit
) {
    // Lista das suas perguntas na ordem correta
    val perguntas = listOf(
        Pergunta(
            titulo = "Qual a sua faixa etária?",
            tipo = TipoPergunta.BOTAO,
            campo = "idade",
        opcoes = listOf("Menor de 18" to 17, "18 a 30" to 25, "31 a 45 anos" to 35, "Mais de 45 anos" to 45)),
        Pergunta(titulo ="Seu ciclo é regular?", tipo = TipoPergunta.BOTAO, campo = "cicloRegular",
        opcoes =listOf("Sim" to true, "Não" to false, "Não sei" to false)),
        Pergunta(titulo ="Você usa métodos contraceptivos?", tipo = TipoPergunta.BOTAO, campo = "contraceptivo",
        opcoes =listOf("Sim" to true, "Não" to false)),
        Pergunta(titulo ="Qual destes?", tipo = TipoPergunta.BOTAO, campo = "tipoContraceptivo",
        opcoes =listOf("Pilulas" to "Pilulas", "DIU" to "DIU", "Injeção" to "Injeção", "Implanou" to "Implanou", "Apenas preservativos" to "Apenas preservativos")),
        Pergunta(titulo ="Marque o dia da sua ultima menstruação", tipo = TipoPergunta.DATA, campo = "ultimaMenstruacao"),
        Pergunta(titulo ="Qual a duração média do seu ciclo?", tipo = TipoPergunta.NUMERO, campo = "duracaoCiclo")
    )

    var indiceAtual by remember { mutableIntStateOf(0) }
    val perguntaAtual = perguntas[indiceAtual]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Barra de Progresso (Dá um toque especial!)
        LinearProgressIndicator(
            progress = (indiceAtual + 1).toFloat() / perguntas.size,
            modifier = Modifier.fillMaxWidth().clip(CircleShape),
            color = PinkButton
        )
        Spacer(modifier = Modifier.height(80.dp))

        // 1. Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(154.dp, 147.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = perguntaAtual.titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        when (perguntaAtual.tipo) {
            TipoPergunta.NUMERO -> {
                CampoNumero(onValorConfirmado = { valor ->
                    viewModel.atualizarDadosQuiz(perguntaAtual.campo, valor)
                    proximaPergunta(
                        lista = perguntas,
                        atual = indiceAtual,
                        atualizarIndice = { indiceAtual = it },
                        finalizou = {
                        viewModel.onQuizFinish() // Chama o salvamento
                        onQuizComplete()         // Chama a navegação (agora sem erro!)
                    })
                })
            }
            TipoPergunta.DATA -> {
                CampoData(onDataConfirmada = { data ->
                    viewModel.atualizarDadosQuiz(perguntaAtual.campo, data)
                    proximaPergunta(lista = perguntas,
                        atual = indiceAtual,
                        atualizarIndice = { indiceAtual = it },
                        finalizou = {
                            viewModel.onQuizFinish() // Chama o salvamento
                            onQuizComplete()         // Chama a navegação (agora sem erro!)
                        })
                })
            }
            TipoPergunta.BOTAO -> {
                BotoesOpcao(opcoes = perguntaAtual.opcoes, onSelecionado = { valor ->
                    viewModel.atualizarDadosQuiz(perguntaAtual.campo, valor)
                    proximaPergunta(lista = perguntas,
                        atual = indiceAtual,
                        atualizarIndice = { indiceAtual = it },
                        finalizou = {
                            viewModel.onQuizFinish() // Chama o salvamento
                            onQuizComplete()         // Chama a navegação (agora sem erro!)
                        })
                })
            }
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}

@Composable
fun BotoesOpcao(
    opcoes: List<Pair<String, Any>>,
    onSelecionado: (Any) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Espaçamento entre os botões
    ) {
        opcoes.forEach { (texto, valor) ->
            Button(
                onClick = {
                    onSelecionado(valor)
                },
                modifier = Modifier.size(width = 304.dp, height = 44.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GrayButton),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = texto, fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoData(
    onDataConfirmada: (String) -> Unit
) {
    var showDatePicker by remember { androidx.compose.runtime.mutableStateOf(false) }
    val datePickerState = androidx.compose.material3.rememberDatePickerState()

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Espaçamento entre os botões
    ) {
        OutlinedButton(onClick = { showDatePicker = true }) {
            Icon(Icons.Default.CalendarToday, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(text = datePickerState.selectedDateMillis?.let {
                formatarData(it)
            } ?: "Selecionar data")
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val selectedDate = formatarData(datePickerState.selectedDateMillis)
                        showDatePicker = false
                        onDataConfirmada(selectedDate)
                    }) { Text("Confirmar") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@Composable
fun CampoNumero(
    onValorConfirmado: (Int) -> Unit) {
    var textoDigitado by remember { androidx.compose.runtime.mutableStateOf("") }
    var erroValidacao by remember { androidx.compose.runtime.mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Espaçamento entre os botões
    ) {
        Text(
            text = "O intervalo deve ser entre 21 e 35 dias",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = textoDigitado,
            onValueChange = { novoTexto ->
                if (novoTexto.all { it.isDigit() } && novoTexto.length <= 2) {
                    textoDigitado = novoTexto
                    val valor = novoTexto.toIntOrNull()
                    erroValidacao = valor != null && valor !in 21..35
                }
            },
            label = { Text("Duração") },
            suffix = { Text("dias") }, // Fica bonitinho mostrar a unidade dentro do campo
            isError = erroValidacao, // Ativa a cor vermelha do Material Design
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val valorFinal = textoDigitado.toIntOrNull() ?: 28
                onValorConfirmado(valorFinal) },
            // O botão só habilita se o valor for válido
            enabled = !erroValidacao && textoDigitado.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = PinkButton
            ),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Próximo", color = Color.White, fontSize = 16.sp)
        }
    }
}

fun proximaPergunta(lista: List<Pergunta>, atual: Int, atualizarIndice: (Int) -> Unit, finalizou: () -> Unit) {
    if (atual < lista.size - 1) {
        atualizarIndice(atual + 1)
    } else {
        finalizou()
    }
}

fun formatarData(millis: Long?): String {
    if (millis == null) return ""
    val data = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    // Formato padrão para APIs: AAAA-MM-DD
    return data.format(DateTimeFormatter.ISO_LOCAL_DATE)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizAScreenPreview() {
    Dalia2Theme {
        //QuizPeriodScreen{}
    }
}