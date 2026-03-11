package com.example.dalia2.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter


//Lista os destinos
enum class Destination(
    val route: String,
    val icon: @Composable () -> Painter ,
    val label: String
) {
    Home("home", { CustomIcons.home() }, "Início"),
    Calendar("calendar", { CustomIcons.calendar() }, "Calendário"),
    Bot("bot", { CustomIcons.bot() }, "DáliaBot"),
    Forum("forum", { CustomIcons.forum() }, "Fórum"),
    Settings("settings", { CustomIcons.settings() }, "Perfil");

    companion object {
        val items = entries.toList()  // Lista de todos os destinos
    }
}