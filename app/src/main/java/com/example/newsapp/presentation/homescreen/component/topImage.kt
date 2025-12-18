package com.example.newsapp.presentation.homescreen.component

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.example.newsapp.R


@Composable
fun TopImage(
    image: Painter,
    modifier: Modifier = Modifier
) {

    Image(
        modifier = modifier,
        painter = image,
        contentDescription = stringResource(id = R.string.top_image_section),
        contentScale = ContentScale.Crop,
    )

}