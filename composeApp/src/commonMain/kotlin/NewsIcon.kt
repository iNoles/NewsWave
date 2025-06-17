package com.jonathansteele.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NewsIcon(
    article: News.Article,
    modifier: Modifier = Modifier
) {
    article.urlToImage?.let { imageUrl ->
        Surface(
            tonalElevation = 4.dp,
            shadowElevation = 6.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Better for showing images at all sizes
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
