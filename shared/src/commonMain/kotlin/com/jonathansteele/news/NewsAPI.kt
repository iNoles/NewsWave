package com.jonathansteele.news

import kotlinx.coroutines.flow.Flow

expect fun fetchAllHeadlines(source: String, platformContext: PlatformContext): Flow<List<News.Article>?>
