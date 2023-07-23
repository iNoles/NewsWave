package com.jonathansteele.news

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun NewsIcon(article: News.Article, modifier: Modifier)