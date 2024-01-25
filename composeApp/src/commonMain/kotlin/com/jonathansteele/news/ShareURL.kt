package com.jonathansteele.news

import androidx.compose.runtime.Composable

@Composable
expect fun ShareURL(
    article: News.Article,
    platformContext: PlatformContext,
)
