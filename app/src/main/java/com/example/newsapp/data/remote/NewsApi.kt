package com.example.newsapp.data.remote

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
//BuildConfig.NEWS_API_KEY
val API_KEY = BuildConfig.NEWS_API_KEY

const val BASE_URL = "https://newsapi.org/v2/"

// Retrofit interface for fetching news data from the API
interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}

