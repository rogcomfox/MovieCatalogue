package com.rogcomfox.moviecatalogue.util

import android.content.Context
import android.widget.Toast
import com.rogcomfox.core.source.local.database.Constant
import java.util.Locale

fun Context.toastShort(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.toastLong(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun Context.setAppLocale(languageCode: String): Context {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return createConfigurationContext(config)
}

fun Context.getAppLocale(): String {
    val prefs = this.getSharedPreferences(Constant.MAIN_PREF, Context.MODE_PRIVATE)
    return prefs.getString(Constant.APP_LANG, Constant.INDONESIA_LANG)!!
}