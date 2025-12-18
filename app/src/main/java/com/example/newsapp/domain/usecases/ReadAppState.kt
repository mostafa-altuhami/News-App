package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.manager.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Use case for reading the app's first launch state
class ReadAppState @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    operator fun invoke() : Flow<Boolean> {
        return dataStoreManager.readAppEntry()
    }
}