package com.jonathansteele.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewsItem(
    article: News.Article,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    newsSelected: (person: News.Article) -> Unit
) {
    Card(modifier.padding(16.dp)) {
        ListItem(
            modifier = modifier.clickable {
                newsSelected(article)
            },
            leadingContent = {
                NewsIcon(article, iconModifier)
            },
            headlineContent = { Text(article.title) },
            supportingContent = {
                article.description?.let { Text(it) }
            }
        )
    }
}