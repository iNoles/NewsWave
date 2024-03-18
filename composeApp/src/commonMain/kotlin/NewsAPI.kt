package com.jonathansteele.news

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

expect fun fetchAllHeadlines(
    source: String,
    platformContext: PlatformContext,
): Flow<ImmutableList<News.Article>?>
