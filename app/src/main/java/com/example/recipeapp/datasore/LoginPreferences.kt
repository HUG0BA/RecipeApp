package com.example.recipeapp.datasore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.preferencesOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

data class LoginPreferences(
    val isLogged: Boolean,
    val loggedUserId: Int
)

class LoginPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
) {

    private object PreferencesKeys {
        val IS_LOGGED = booleanPreferencesKey("is_logged")
        val LOGGED_USER_ID = intPreferencesKey("logged_user_id")
    }

    val loginPreferencesFlow: Flow<LoginPreferences> = dataStore.data
        .map{ preferences ->
            mapLoginPreferences(preferences)
        }

    suspend fun updateIsLogged(loggedUserId: Int){
        dataStore.edit{ preferences ->
            preferences[PreferencesKeys.IS_LOGGED] = true
            preferences[PreferencesKeys.LOGGED_USER_ID] = loggedUserId
        }
    }

    suspend fun fetchInitialPreferences() =
        mapLoginPreferences(dataStore.data.first().toPreferences())

    private fun mapLoginPreferences(preferences: Preferences): LoginPreferences{

        val isLogged = preferences[PreferencesKeys.IS_LOGGED] ?: false
        val loggedUserId = preferences[PreferencesKeys.LOGGED_USER_ID] ?: -1

        return LoginPreferences(isLogged, loggedUserId)
    }
}