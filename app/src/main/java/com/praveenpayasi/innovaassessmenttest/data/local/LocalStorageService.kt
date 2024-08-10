package com.praveenpayasi.innovaassessmenttest.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class LocalStorageService constructor(
    private val appSharedPref: SharedPreferences,
    private val gson: Gson
) {
    companion object {
        private const val KEY_ASTRONOMY = "key_astronomy"
    }

    suspend fun saveString(key: String, value: String) {
        withContext(Dispatchers.IO) {
            appSharedPref.edit().putString(key, value).apply()
        }
    }

    suspend fun <T> saveAstronomy(value: T) {
        val jsonString = gson.toJson(value)
        saveString(KEY_ASTRONOMY, jsonString)
    }

    fun <T> getAstronomy(clazz: Class<T>): Flow<T> = flow {
        val jsonString = appSharedPref.getString(KEY_ASTRONOMY, null)
        val obj = jsonString?.let { gson.fromJson(it, clazz) }
        if (obj != null) {
            emit(obj)
        }
    }
}