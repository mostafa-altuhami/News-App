package com.example.newsapp.data.remote.dto

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
// Represents the source of a news article
data class Source(
    val id: String? = null,
    val name: String
)