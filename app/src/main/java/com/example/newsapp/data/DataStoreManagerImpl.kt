package com.example.newsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.data.PreferenceKeys.APP_ENTRY
import com.example.newsapp.domain.manager.DataStoreManager
import com.example.newsapp.utils.Constants
import com.example.newsapp.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Implementation of DataStoreManager to handle app preferences
class DataStoreManagerImpl @Inject constructor (
     private val context: Context
): DataStoreManager {

    // Save that the app has been entered (first launch flag)
    override suspend fun saveAppEntry() {
        context.dataStore.edit {settings ->
            settings[APP_ENTRY] = true

        }
    }

    // Read the launch flag from DataStore
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { prefrences ->
            prefrences[APP_ENTRY] ?: false
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}