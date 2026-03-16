package com.example.newsapp.domain.usecases.news

import javax.inject.Inject

data class GetSavedArticlesUseCases @Inject constructor(
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val insertArticle: InsertArticle
)