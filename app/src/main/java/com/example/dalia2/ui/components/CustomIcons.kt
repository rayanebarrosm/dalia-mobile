package com.example.dalia2.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.dalia2.R

object CustomIcons {
    @Composable
    fun home(): Painter = painterResource(id = R.drawable.home_nav)

    @Composable
    fun forum(): Painter = painterResource(id = R.drawable.chat_nav)

    @Composable
    fun bot(): Painter = painterResource(id = R.drawable.flower_nav)

    @Composable
    fun calendar(): Painter = painterResource(id = R.drawable.calendar_nav)

    @Composable
    fun settings(): Painter = painterResource(id = R.drawable.perfil)
}