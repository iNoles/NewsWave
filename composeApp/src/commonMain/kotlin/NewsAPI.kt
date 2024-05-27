package com.jonathansteele.news

import fuel.Fuel
import fuel.get
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

fun fetchAllHeadlines(source: String) =
    flow {
        val json = Json { ignoreUnknownKeys = true }
        val news =
            Fuel.get(
                url = "https://newsapi.org/v2/top-headlines",
                parameters =
                    listOf(
                        "country" to "us",
                        "apiKey" to BuildKonfig.NEWS_API_KEY,
                        "category" to source,
                    ),
            ).json(json)
        news.fold({
            emit(it)
        }, {
            println(it.message)
        })
    }
