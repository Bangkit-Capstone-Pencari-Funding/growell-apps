package com.growell.data

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsUtil {

    private const val PREFS_NAME = "MyPrefs"
    private const val TOKEN_KEY = "token"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getPrefs(context).edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getPrefs(context).getString(TOKEN_KEY, null)
    }

    fun clearToken(context: Context) {
        val editor = getPrefs(context).edit()
        editor.remove(TOKEN_KEY)
        editor.apply()
    }

}