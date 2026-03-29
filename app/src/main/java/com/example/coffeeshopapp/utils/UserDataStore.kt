package com.example.coffeeshopapp.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.coffeeshopapp.data.model.UserModel

// Extension
val Context.userDataStore by preferencesDataStore("user_prefs")

class UserDataStore(private val context: Context) {

    object PreferencesKeys {
        val ONBOARDING_SEEN = booleanPreferencesKey("onboarding_seen")
    }

    suspend fun setOnboardingSeen() {
        context.userDataStore.edit { prefs ->
            prefs[PreferencesKeys.ONBOARDING_SEEN] = true
        }
    }

    fun isOnboardingSeen(): Flow<Boolean> {
        return context.userDataStore.data.map { prefs ->
            prefs[PreferencesKeys.ONBOARDING_SEEN] ?: false
        }
    }
    companion object {
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PHONE = stringPreferencesKey("phone")
        val BIRTHDAY = stringPreferencesKey("birthday")
        val IMAGE = stringPreferencesKey("image")
    }

    val userFlow: Flow<UserModel> =
        context.userDataStore.data.map { prefs ->
            UserModel(
                prefs[NAME] ?: "",
                prefs[EMAIL] ?: "",
                prefs[PHONE] ?: "",
                prefs[BIRTHDAY] ?: "",
                prefs[IMAGE] ?: ""
            )
        }



    suspend fun saveUser(user: UserModel) {
        context.userDataStore.edit { prefs ->
            prefs[NAME] = user.name
            prefs[EMAIL] = user.email
            prefs[PHONE] = user.phone
            prefs[BIRTHDAY] = user.birthday
            prefs[IMAGE] = user.image
        }
    }

    suspend fun clearUser() {
        context.userDataStore.edit { prefs ->
            prefs.clear()
        }
    }


}