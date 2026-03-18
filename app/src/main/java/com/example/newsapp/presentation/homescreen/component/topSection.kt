package com.example.newsapp.presentation.homescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.example.newsapp.R
import com.example.newsapp.ui.theme.ButtonColor
import com.example.newsapp.utils.Dimens._10Dp
import com.example.newsapp.utils.Dimens._14Dp
import com.example.newsapp.utils.Dimens._150Dp
import com.example.newsapp.utils.Dimens._1Dp
import com.example.newsapp.utils.Dimens._30Dp
import com.example.newsapp.utils.Dimens._6Dp
import com.example.newsapp.utils.Dimens._90Dp


@Composable
fun TopSection(
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Row (
        modifier = modifier
            .padding(top = _10Dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Image(
            modifier = Modifier
                .padding(start = _10Dp)
                .size(width = _150Dp, height = _90Dp),
            painter = image,
            contentDescription = stringResource(id = R.string.top_image_section),
            contentScale = ContentScale.Crop,
        )


        Button(
            modifier = Modifier
                .padding(end = _10Dp),
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(_30Dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = _1Dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
            contentPadding = PaddingValues(horizontal = _14Dp, vertical = _6Dp)
        ) {

            Text(
                modifier = Modifier
                    .padding(_10Dp),
                text = stringResource(id = R.string.refresh)
            )
        }
    }

}