package com.example.librarymanagementsystem.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

val USER_NAME_KEY = stringPreferencesKey("email")
val PASSWORD_KEY = stringPreferencesKey("password")

suspend fun saveUserName(context: Context, userName: String) {
    context.dataStore.edit { preferences ->
        preferences[USER_NAME_KEY] = userName
    }
}

suspend fun savePassword(context: Context, password: String){
    context.dataStore.edit { preferences ->
        preferences[PASSWORD_KEY] = password
    }
}

suspend fun getUserName(context: Context): String? {
    return context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY]
    }.first()
}

suspend fun getPassword(context: Context): String? {
    return context.dataStore.data.map { preferences ->
        preferences[PASSWORD_KEY]
    }.first()
}