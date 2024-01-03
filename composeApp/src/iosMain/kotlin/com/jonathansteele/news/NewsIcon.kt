package com.jonathansteele.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
actual fun NewsIcon(article: News.Article, modifier: Modifier) {
    article.urlToImage?.let {
        FetchImage(it)?.let { it1 ->
            Image(it1, contentDescription = null, modifier = modifier.size(64.dp))
        }
    }
}
