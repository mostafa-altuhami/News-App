package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.repository.LocalNewsRepository
import javax.inject.Inject

class GetArticles @Inject constructor(
    private val localNewsRepository: LocalNewsRepository
) {

    operator fun invoke() = localNewsRepository.getArticles()

}