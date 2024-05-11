package com.jonathansteele.news.coil

import coil3.annotation.ExperimentalCoilApi
import coil3.network.NetworkClient
import coil3.network.NetworkRequest
import coil3.network.NetworkResponse
import coil3.network.NetworkResponseBody
import fuel.Fuel
import fuel.HttpResponse
import fuel.method
import okio.Buffer
import kotlin.jvm.JvmInline

@OptIn(ExperimentalCoilApi::class)
@JvmInline
internal value class FuelNetworkClient(
    private val fuel: Fuel,
) : NetworkClient {
    override suspend fun <T> executeRequest(
        request: NetworkRequest,
        block: suspend (response: NetworkResponse) -> T,
    ) : T {
        val method = fuel.method(
            url = request.url,
            method = request.method,
            body = request.body.toString()
        )
        return block(method.toNetworkResponse(request))
    }
}

@OptIn(ExperimentalCoilApi::class)
fun HttpResponse.toNetworkResponse(request: NetworkRequest) =
    NetworkResponse(
        request = request,
        code = statusCode,
        body = NetworkResponseBody(Buffer().apply { writeUtf8(body) } )
    )