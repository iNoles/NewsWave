package com.jonathansteele.news

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class News(
    @Serializable(PersistentListSerializer::class)
    val articles: ImmutableList<Article>?,
    val status: String,
    // ok
    val totalResults: Int?,
    // 34
) {
    @Serializable
    @Immutable
    data class Article(
        val author: String?,
        // Robyn Dixon, Mary Ilyushina
        val description: String?,
        // The Belarusian president described his conversations with the Russian president and the mercenary chief in unusually frank comments on Tuesday.
        val publishedAt: Instant,
        // 2023-06-28T00:24:40Z
        val title: String,
        // Lukashenko claims he persuaded Putin not to kill Wagner boss Prigozhin - The Washington Post
        val url: String,
        // https://www.washingtonpost.com/world/2023/06/27/prigozhin-lukashenko-putin-wagner-rebellion/
        val urlToImage: String?,
        // https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/YRCHKQZ6FCJRV4RMTRDNY4EMKU_size-normalized.jpg&w=1440
    )
}
