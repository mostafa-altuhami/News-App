package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.model.ArticleUi
import com.example.newsapp.domain.repository.LocalNewsRepository
import javax.inject.Inject

class InsertArticle @Inject constructor(
    private val localNewsRepository: LocalNewsRepository
) {
    suspend operator fun invoke(article: ArticleUi) {
        localNewsRepository.insert(article)
    }

}