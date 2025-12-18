@file:OptIn(ExperimentalFoundationApi::class)

package com.example.newsapp.presentation.homescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.presentation.common.Articles
import com.example.newsapp.presentation.common.SearchBarSection
import com.example.newsapp.presentation.homescreen.component.TopImage
import com.example.newsapp.presentation.navgraph.Route
import com.example.newsapp.utils.Dimens._10Dp
import com.example.newsapp.utils.Dimens._12Dp
import com.example.newsapp.utils.Dimens._14Sp
import com.example.newsapp.utils.Dimens._150Dp
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.utils.Dimens._60Dp
import com.example.newsapp.utils.Dimens._8Dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val homeViewModel: HomeScreenViewModel = hiltViewModel()
    val news = homeViewModel.news.collectAsLazyPagingItems()

    // Create a concatenated string of top 10 news titles for marquee effect
    val titles by remember {
        derivedStateOf {
                if (news.itemCount > 10) {
                    news.itemSnapshotList.items
                        .slice(0..9)
                        .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
                } else
                    ""
        }
    }


    Column (
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {

        TopImage(
            modifier = Modifier
                .size(width = _150Dp, height = _60Dp)
                .padding(top = _16Dp),
            image = painterResource(id = R.drawable.app_name)
        )

        SearchBarSection(
            modifier = Modifier
                .padding(_12Dp)
                .fillMaxWidth(),
            text = TextFieldValue(""),
            readOnly = true,
            onValueChange = {},
            onClick = {
                navController.navigate(Route.SearchScreen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },

        )

        Spacer(modifier = Modifier.height(_10Dp))

        // Marquee scrolling titles of top news
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = _8Dp)
                .basicMarquee(),
            text = titles,
            maxLines = 1,
            fontSize = _14Sp
        )


        Articles(
            news = news,
            navController = navController
        )


    }
}
