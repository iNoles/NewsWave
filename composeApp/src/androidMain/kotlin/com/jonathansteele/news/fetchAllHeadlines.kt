package com.jonathansteele.news

import android.util.Log
import fuel.FuelBuilder
import fuel.Request
import fuel.serialization.toJson
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.OkHttpClient

actual fun fetchAllHeadlines(
    source: String,
    platformContext: PlatformContext,
) = flow {
    val json = Json { ignoreUnknownKeys = true }
    val okhttp =
        OkHttpClient.Builder()
            .cache(
                Cache(
                    directory = platformContext.androidContext.cacheDir,
                    // $0.05 worth of phone storage in 2020
                    maxSize = 50L * 1024L * 1024L, // 50 MiB
                ),
            ).build()
    val fuel = FuelBuilder().config(okhttp).build()
    val request =
        Request.Builder()
            .url("https://newsapi.org/v2/top-headlines")
            .parameters(
                listOf(
                    "country" to "us",
                    "apiKey" to BuildConfig.NEWS_API_KEY,
                    "category" to source,
                ),
            )
            .build()
    val news =
        fuel.get(request)
            .toJson(
                json = json,
                deserializationStrategy = News.serializer(),
            )
    news.fold({
        emit(it?.articles)
    }, {
        Log.e("fetchAllHeadlines", "Can't process Headlines", it)
    })
}
