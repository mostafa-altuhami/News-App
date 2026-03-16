package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.repository.GetNewsRepository
import javax.inject.Inject

class GetNews @Inject constructor(
    private val getNewsRepository: GetNewsRepository
) {
    operator fun invoke (sources: List<String>) = getNewsRepository.getNews(sources)
}