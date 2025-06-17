package com.jonathansteele.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NewsItem(
    article: News.Article,
    modifier: Modifier = Modifier,
    onArticleClick: (News.Article) -> Unit,
) {
    Surface(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        tonalElevation = 6.dp,
        shadowElevation = 10.dp,
        shape = MaterialTheme.shapes.medium,
        onClick = { onArticleClick(article) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            NewsIcon(
                article = article,
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(90.dp),
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 8.dp),
            )
            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }
    }
}
