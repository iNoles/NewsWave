package com.jonathansteele.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailView(
    article: News.Article?,
    selectedArticle: MutableState<News.Article?>,
    navigationEnabled: Boolean = false
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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NewsDetailContent(
    article: News.Article?,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium
) {
    article?.let {
        Surface(
            modifier = modifier.padding(16.dp),
            tonalElevation = 4.dp,
            shape = shape
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                NewsIcon(
                    article = article,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(shape)
                )
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "By ${article.author}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                NewsDate(article.publishedAt)
                Text(
                    text = article.description ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    } ?: Text("Select an article", modifier = modifier.padding(16.dp))
}

@Composable
fun NewsDate(instant: Instant) {
    Text(text = formattingDate(instant))
}
