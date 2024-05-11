package com.jonathansteele.news.coil

import coil3.Uri
import coil3.annotation.InternalCoilApi
import coil3.util.FetcherServiceLoaderTarget

@OptIn(InternalCoilApi::class)
internal class FuelNetworkFetcherServiceLoaderTarget : FetcherServiceLoaderTarget<Uri> {
    override fun factory() = fuelNetworkFetcherFactory()

    override fun type() = Uri::class

    override fun priority() = 1 // FuelNetworkFetcher takes precedence over KtorNetworkFetcher.
}
