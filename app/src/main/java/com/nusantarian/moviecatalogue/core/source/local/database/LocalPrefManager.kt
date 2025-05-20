package com.nusantarian.moviecatalogue.core.source.local.database

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class LocalPrefManager(context: Context) {
    var prefs: SharedPreferences =
        context.getSharedPreferences(Constant.MAIN_PREF, Context.MODE_PRIVATE)

    fun setPrefsData(key: String, value: Any?) {
        prefs.edit {
            when (value) {
                is String? -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
            }
        }
    }

    inline fun <reified T : Any> getPrefsData(key: String, defaultValue: T? = null): T {
        with(prefs) {
            return when (T::class) {
                String::class -> getString(key, defaultValue as? String ?: "") as T
                Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
                Boolean::class -> getBoolean(key, defaultValue as? Boolean == false) as T
                Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
                Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }
    }

    fun removePrefsData(key: String) = prefs.edit { remove(key) }
}