package com.jonathansteele.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailView(
    article: News.Article?,
    selectedArticle: MutableState<News.Article?>,
    navigationEnabled: Boolean,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    if (navigationEnabled) {
                        IconButton(onClick = {
                            selectedArticle.value = null
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Pop Back Navigation Stack",
                            )
                        }
                    }
                },
                actions = {
                    if (article != null) {
                        ShareURL(article)
                    }
                },
                title = { Text("") },
            )
        },
    ) {
        NewsDetailContent(article = article, modifier = Modifier.padding(it))
    }
}

@Composable
fun NewsDetailContent(
    article: News.Article?,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
) {
    article?.let {
        Column(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            NewsIcon(
                article,
                iconModifier.heightIn(min = 180.dp)
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.medium),
            )
            Text(article.title)
            Text("By ${article.author}")
            NewsDate(article.publishedAt)
            Text(article.description ?: "")
        }
    } ?: Text("Select an article")
}

@Composable
fun NewsDate(instant: Instant) {
    Text(text = formattingDate(instant))
}
