package com.example.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

// Interface to manage app preferences using DataStore
interface DataStoreManager {

    suspend fun saveAppEntry()

    fun readAppEntry() : Flow<Boolean>
}