package com.example.newsapp.presentation.articledetailsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleUi
import com.example.newsapp.domain.repository.LocalNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveNewsViewModel @Inject constructor(
    private val repository: LocalNewsRepository
): ViewModel() {

    val articles = repository.getArticles().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly, // // Start collecting immediately to have articles ready
        initialValue = emptyList()
    )

    fun toggleSaveArticle(article: ArticleUi) {
        viewModelScope.launch {
            val cleanUrl = article.url.trim()

            val existingArticle = articles.value.find {
                it.url.trim() == cleanUrl
            }

            if (existingArticle != null) {
                repository.delete(article)
            } else {
                repository.insert(article)
            }
        }
    }

}