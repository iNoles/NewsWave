package com.jonathansteele.news

import fuel.Fuel
import fuel.get
import fuel.serialization.toJson
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

actual fun fetchAllHeadlines(
    source: String,
    platformContext: PlatformContext,
) = flow {
    val json = Json { ignoreUnknownKeys = true }
    val news =
        Fuel.get(
            url = "https://newsapi.org/v2/top-headlines",
            parameters =
                listOf(
                    "country" to "us",
                    "apiKey" to BuildConfig.NEWS_API_KEY,
                    "category" to source,
                ),
        ).toJson(json = json, deserializationStrategy = News.serializer())
    news.fold({
        emit(it?.articles)
    }, {
        println(it)
    })
}
