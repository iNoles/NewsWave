package com.jonathansteele.news

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
actual fun NewsIcon(article: News.Article, modifier: Modifier) {
    article.urlToImage?.let {
        AsyncImage(
            model = it,
            contentDescription = null,
            modifier = modifier.size(64.dp)
        )
    }
}
