package com.jonathansteele.news

import fuel.Fuel
import fuel.get
import fuel.serialization.toJson
import kotlinx.coroutines.flow.flow

actual fun fetchAllHeadlines(source: String, platformContext: PlatformContext) = flow {
    val news = Fuel.get(
        url = "https://newsapi.org/v2/top-headlines/sources",
        parameters = listOf("apiKey" to BuildConfig.NEWS_API_KEY, "category" to source)
    ).toJson(deserializationStrategy = News.serializer())
    news.fold({
        emit(it?.articles)
    }, {
        println(it)
    })
}
