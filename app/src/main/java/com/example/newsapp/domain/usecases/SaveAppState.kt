package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.manager.DataStoreManager
import javax.inject.Inject

// Use case for saving the app's first launch state
class SaveAppState @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    suspend operator fun invoke() {
        dataStoreManager.saveAppEntry()
    }
}