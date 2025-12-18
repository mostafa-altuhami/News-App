package com.example.newsapp.presentation.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.utils.Dimens._12Dp
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.presentation.common.Articles
import com.example.newsapp.presentation.common.SearchBarSection


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val searchViewModel: SearchScreenViewModel = hiltViewModel()


    // Create a FocusRequester object to programmatically request focus for the search bar
    val focusRequester = remember {
        FocusRequester()
    }

    // Use LaunchedEffect to request focus once when the composable is first displayed
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

   val query = searchViewModel.query.collectAsState()
    val items = searchViewModel.items.collectAsLazyPagingItems()



    Column (
        modifier = modifier
            .padding(_16Dp)
            .statusBarsPadding(),
    ) {

        SearchBarSection(
            modifier = Modifier
                .padding(all = _12Dp)
                .focusRequester(focusRequester),
            text = query.value,
            readOnly = false,
            onValueChange = {
               searchViewModel.search(it)
            },
            onSearch = {
                searchViewModel.search(query.value)
            }
        )

        if (query.value.text.isNotEmpty())
            Articles(
                news = items,
                navController = navController
            )


    }

}

