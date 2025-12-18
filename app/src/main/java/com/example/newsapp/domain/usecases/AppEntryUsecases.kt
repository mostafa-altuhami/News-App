package com.example.newsapp.domain.usecases

import javax.inject.Inject

// Aggregates all app entry related use cases
data class AppEntryUseCases @Inject constructor(
    val readAppState: ReadAppState,
    val saveAppState: SaveAppState
)
