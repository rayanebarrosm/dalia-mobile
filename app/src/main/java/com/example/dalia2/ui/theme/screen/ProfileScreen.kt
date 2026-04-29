package com.example.dalia2.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalia2.R
import com.example.dalia2.ui.theme.Dalia2Theme
import com.example.dalia2.ui.theme.GrayButton
import com.example.dalia2.ui.theme.PinkButton
import com.example.dalia2.ui.theme.LightPink
import com.example.dalia2.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    var showDialog by remember { mutableStateOf(false) }
    var isPregnancyMode by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD286C9),
                        Color(0xFFF8ADCD),
                        Color(0xFFF18FB8)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Perfil",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
                );

            Spacer(modifier = Modifier.height(20.dp))

            //Imagem do perfil
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)

            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 14.dp, vertical = 8.dp)

                ) {

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Text(text = "Informações", fontSize = 20.sp,  fontWeight = FontWeight.SemiBold);

                        Button(
                            onClick = { /* tela activity*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = PinkButton)
                        ) {
                            Text("Editar", color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    InfoSection(
                        label = "Nome",
                        value = "ee"//state.name//dado do banco
                    );

                    InfoSection(
                        label = "Email",
                        value = "ee@gmail.com"//state.email//dado do banco de dados
                    );

                    InfoSection(
                        label = "Idade",
                        value = "19"//state.age//dado do banco de dados
                    );


                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Seção de configurações
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    SettingsButton (
                        text = "Idioma",
                        icon = R.drawable.idioma_icon,
                        onClick = { /* Mudar idioma */ },
                        backgroundColor = LightPink
                    )

                    SettingsButton (
                        text = "Informações pessoais",
                        icon = R.drawable.person,
                        onClick = { /* Ver planos */ },
                        backgroundColor = LightPink

                    )

                    SettingsButton(
                        text = "Planos de pagamento",
                        icon = R.drawable.cifrao_icon,
                        onClick = { /* Ver planos */ },
                        backgroundColor = LightPink

                    )

                    SettingsButton (
                        text = "Ajuda",
                        icon = R.drawable.search_icon,
                        onClick = { /* Ajuda */ },
                        backgroundColor = LightPink
                    )

                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PinkButton
                        )
                    ){
                        Text("Mudar para modo gravidez")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Zona de alerta
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                colors = CardDefaults.cardColors( containerColor = Color.White)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                    ,
                ) {
                    Text(
                        text = "Zona de perigo!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold

                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    SettingsButton(
                        text = "Denunciar",
                        icon = R.drawable.alert,
                        backgroundColor = PinkButton,
                        onClick = { /* Denunciar */ },
                    )

                    Button(
                        onClick = { /* Excluir conta */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors( containerColor = PinkButton)
                    ) {
                        Text("Excluir conta")
                    }
                }
            }

            //Pop-up
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (isPregnancyMode) R.drawable.pop_heartcalendar else R.drawable.pop_pregnance
                            ),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(64.dp)
                        )
                    },
                    title = { Text("Atenção") },
                    text = {
                        Text("Você está prestes a mudar para o modo ${if (isPregnancyMode) "menstruação" else "gravidez"}!")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            isPregnancyMode = !isPregnancyMode
                            showDialog = false
                            // Adicione aqui a navegação se necessário
                        }) {
                            Text("Confirmar", color = PinkButton, fontWeight = FontWeight.Bold)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancelar", color = Color.Gray)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun InfoSection(
    label: String,
    value: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)

    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            color = GrayButton,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SettingsButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.Transparent
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        }
    }

}

@Preview(
    widthDp = 500,
    heightDp = 1000
)
@Composable
fun ProfileScreenPreview() {
    Dalia2Theme {
        ProfileScreen()
    }
}