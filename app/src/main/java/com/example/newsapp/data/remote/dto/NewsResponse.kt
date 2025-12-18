package com.example.newsapp.data.remote.dto

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
// Represents the response from the news API
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)