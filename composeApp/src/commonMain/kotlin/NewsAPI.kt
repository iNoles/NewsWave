package com.jonathansteele.news

import com.jonathansteele.newswave.BuildKonfig
import fuel.Fuel
import fuel.get
import fuel.serialization.toJson
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

fun fetchAllHeadlines(source: String) =
    flow {
        val json = Json { ignoreUnknownKeys = true }
        val news =
            Fuel
                .get(
                    url = "https://newsapi.org/v2/top-headlines",
                    parameters =
                        listOf(
                            "country" to "us",
                            "apiKey" to BuildKonfig.NEWS_API_KEY,
                            "category" to source,
                        ),
                ).toJson(
                    json = json,
                    deserializationStrategy = News.serializer(),
                )
        news.fold({
            emit(it)
        }, {
            println(it.message)
        })
    }
