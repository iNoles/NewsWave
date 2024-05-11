package com.jonathansteele.news.coil

import coil3.annotation.ExperimentalCoilApi
import coil3.network.CacheStrategy
import coil3.network.NetworkClient
import coil3.network.NetworkFetcher
import fuel.Fuel

@OptIn(ExperimentalCoilApi::class)
fun fuelNetworkFetcherFactory() =
    NetworkFetcher.Factory(
        networkClient = { Fuel.asNetworkClient() },
        cacheStrategy = { CacheStrategy() },
    )

@OptIn(ExperimentalCoilApi::class)
fun Fuel.asNetworkClient(): NetworkClient {
    return FuelNetworkClient(this)
}
