package com.jonathansteele.news

import com.github.kittinunf.result.Result
import fuel.HttpResponse
import kotlinx.serialization.json.Json

expect fun HttpResponse.json(json: Json): Result<News?, Throwable>
