package com.example.dalia2.ui.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageManager {

    /**
     * Define o idioma do aplicativo
     * @param languageTag Código do idioma (ex: "pt", "en", "es")
     *                    Passe null para seguir o sistema
     */
    fun setAppLanguage(languageTag: String?) {
        val localeList = if (languageTag.isNullOrEmpty()) {
            LocaleListCompat.getEmptyLocaleList()  // Segue o sistema
        } else {
            LocaleListCompat.forLanguageTags(languageTag)
        }
        AppCompatDelegate.setApplicationLocales(localeList)
    }

    /**
     * Retorna o idioma atualmente selecionado
     */
    fun getCurrentLanguage(): String? {
        return AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag()
    }

    /**
     * Retorna o nome do idioma atual para exibição
     */
    fun getCurrentLanguageDisplay(): String {
        return when (getCurrentLanguage()) {
            null -> "Seguir sistema"
            "pt" -> "Português"
            "en" -> "English"
            "es" -> "Español"
            else -> "Português"
        }
    }
}