package com.jonathansteele.news

import com.github.kittinunf.result.Result
import fuel.HttpResponse
import fuel.serialization.toJson
import kotlinx.serialization.json.Json

actual fun HttpResponse.json(json: Json): Result<News?, Throwable> =
    toJson(
        json = json,
        deserializationStrategy = News.serializer()
    )
