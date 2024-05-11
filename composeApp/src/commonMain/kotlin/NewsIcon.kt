package com.jonathansteele.news

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest

@Composable
fun NewsIcon(
    article: News.Article,
    modifier: Modifier,
) {
    article.urlToImage?.let {
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(it)
                .build(),
            contentDescription = null,
            modifier = modifier.size(64.dp),
        )
    }
}
