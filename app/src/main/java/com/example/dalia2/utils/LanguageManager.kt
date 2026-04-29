package com.example.dalia2.utils

import android.content.Context
import android.os.Build
import android.os.LocaleList
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
}