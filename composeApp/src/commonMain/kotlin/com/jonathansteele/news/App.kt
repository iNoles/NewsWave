package com.jonathansteele.news

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme {
        NewsView()
    }
}

@Composable
fun NewsView() {
    val selectedArticle: MutableState<News.Article?> = remember { mutableStateOf(null) }
    BoxWithConstraints {
        if (maxWidth.value > 1000) {
            TwoColumnsLayout(selectedArticle)
        } else {
            SingleColumnLayout(selectedArticle)
        }
    }
}

@Composable
fun SingleColumnLayout(selectedArticle: MutableState<News.Article?>) {
    selectedArticle.value?.let {
        NewsDetailView(it, selectedArticle, false)
    } ?: NewsList(selectedArticle)
}

@Composable
fun TwoColumnsLayout(selectedArticle: MutableState<News.Article?>) {
    Row(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(0.4f), contentAlignment = Alignment.Center) {
            NewsList(selectedArticle)
        }
        NewsDetailView(selectedArticle.value, selectedArticle, false)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsList(selectedArticle: MutableState<News.Article?>) {
    val tabState = remember { mutableStateOf(TabState.GENERAL) }
    val scroll = rememberScrollState()
    val platformContext = getPlatformContext()
    val newsState = fetchAllHeadlines(
        source = tabState.value.name.lowercase(),
        platformContext = platformContext
    ).collectAsState(emptyList())
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("News") }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            FilterTabs(tabState = tabState, scrollState = scroll)
            ListBody(newsState = newsState, selectedArticle = selectedArticle)
        }
    }
}

@Composable
fun FilterTabs(tabState: MutableState<TabState>, scrollState: ScrollState) {
    val coroutineScope = rememberCoroutineScope()
    ScrollableTabRow(selectedTabIndex = TabState.values().toList().indexOf(tabState.value)) {
        TabState.values().forEach {
            Tab(
                text = { Text(it.name) },
                selected = tabState.value == it,
                onClick = {
                    tabState.value = it
                    coroutineScope.launch {
                        scrollState.scrollTo(0)
                    }
                }
            )
        }
    }
}

@Composable
fun ListBody(
    newsState: State<List<News.Article>?>,
    selectedArticle: MutableState<News.Article?>
) {
    LazyColumn {
        items(newsState.value ?: emptyList()) { article ->
            NewsItem(article) {
                selectedArticle.value = it
            }
        }
    }
}
