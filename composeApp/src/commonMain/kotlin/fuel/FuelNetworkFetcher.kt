package com.jonathansteele.news.fuel

import coil3.annotation.ExperimentalCoilApi
import coil3.network.NetworkClient
import coil3.network.NetworkFetcher
import fuel.Fuel

@OptIn(ExperimentalCoilApi::class)
fun fuelNetworkFetcherFactory() = NetworkFetcher.Factory(
    networkClient = { Fuel.asNetworkClient() },
)

fun Fuel.asNetworkClient(): NetworkClient = FuelNetworkClient(this)