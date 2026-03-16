package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.repository.GetNewsRepository
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val getNewsRepository: GetNewsRepository
) {

    operator fun invoke(
        searchQuery: String,
        sources: List<String>
    ) = getNewsRepository.searchNews(searchQuery, sources)

}