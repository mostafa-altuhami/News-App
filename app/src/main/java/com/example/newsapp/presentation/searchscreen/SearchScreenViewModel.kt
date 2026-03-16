package com.example.newsapp.presentation.searchscreen

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _query = MutableStateFlow(TextFieldValue(""))
    val query = _query

    @OptIn(ExperimentalCoroutinesApi::class)
    val items = _query
        .map { it.text }
        .distinctUntilChanged()
        .flatMapLatest { q ->
        if (q.isEmpty())
            flowOf(PagingData.empty())
        else {
            newsUseCases.searchNews(
                searchQuery = q,
                sources =  listOf(
                    "abc-news",
                    "al-jazeera-english",
                    "associated-press",
                    "the-verge",
                    "wired",
                    "techcrunch",
                    "cnn",
                    "fox-news",
                    "google-news",
                    "reuters",
                    "time",
                    "the-washington-post",
                    "independent",
                    "the-wall-street-journal",
                    "engadget",
                    "bloomberg",
                )
            )
        }
    }.cachedIn(viewModelScope)



    fun search(q: TextFieldValue) {
        _query.value = q
    }
}