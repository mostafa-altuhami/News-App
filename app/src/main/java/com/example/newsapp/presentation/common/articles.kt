package com.example.newsapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.utils.Dimens._24Dp
import com.example.newsapp.utils.Dimens._280DP
import com.example.newsapp.utils.Dimens._28Sp
import com.example.newsapp.utils.Dimens._40Dp
import com.example.newsapp.utils.Dimens._60Dp
import com.example.newsapp.utils.Dimens._8Dp
import com.example.newsapp.utils.toUiArticle

@Composable
fun Articles(
    news: LazyPagingItems<Article>,
    navController: NavHostController
) {

    val handlePagingResults = handlePagingResults(news = news)

    if (handlePagingResults) {
        LazyColumn (contentPadding = PaddingValues(
            vertical = _8Dp,
            horizontal = _8Dp
        )
        ){
            items(news.itemCount) {
                ArticleCard(
                    navController = navController,
                    article = news[it].toUiArticle()
                )
            }

        }
    }
}

// Handle Paging loading and error states, show shimmer during those states
@Composable
fun handlePagingResults(news: LazyPagingItems<Article>): Boolean {

    val loadState = news.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            ErrorMessage()
            false
        }
        else -> {
            true
        }
    }
}


@Composable
fun ShimmerEffect() {
    Column (
        verticalArrangement = Arrangement.spacedBy(_24Dp)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect()
        }
    }
    
}

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = _40Dp, vertical = _60Dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(_280DP),
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .padding(top = _24Dp),
            text = stringResource(id = R.string.something_went_wrong),
            fontWeight = FontWeight.Medium,
            fontSize = _28Sp
        )

    }

}
