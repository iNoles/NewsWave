package com.jonathansteele.news

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

val NewsyLightColors =
    lightColorScheme(
        primary = Color(0xFF0A66C2), // Bold blue for headlines & accents
        onPrimary = Color.White,
        secondary = Color(0xFFEF6C00), // Orange for category tags, buttons
        onSecondary = Color.White,
        background = Color(0xFFFDFDFD), // Clean white background
        onBackground = Color(0xFF202124), // Nearly black text for readability
        surface = Color(0xFFF5F5F5), // Card backgrounds
        onSurface = Color(0xFF333333),
    )

val NewsyDarkColors =
    darkColorScheme(
        primary = Color(0xFF82B1FF), // Soft blue for highlights
        onPrimary = Color.Black,
        secondary = Color(0xFFFFB74D), // Warm amber for contrast
        onSecondary = Color.Black,
        background = Color(0xFF121212), // Standard dark mode background
        onBackground = Color(0xFFE0E0E0),
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFFEEEEEE),
    )

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App() {
    val windowVisible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100) // give the window time to fully render
        windowVisible.value = true
    }

    if (windowVisible.value) {
        val isDark = isSystemInDarkTheme()
        val colors = if (isDark) NewsyDarkColors else NewsyLightColors
        MaterialExpressiveTheme(colorScheme = colors) {
            NewsView()
        }
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SingleColumnLayout(selectedArticle: MutableState<News.Article?>) {
    selectedArticle.value?.let {
        NewsDetailView(it, selectedArticle, true)
    } ?: NewsList(selectedArticle)
}

@Composable
fun TwoColumnsLayout(selectedArticle: MutableState<News.Article?>) {
    Row(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(0.4f), contentAlignment = Alignment.Center) {
            NewsList(selectedArticle)
        }
        NewsDetailView(selectedArticle.value, selectedArticle)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsList(selectedArticle: MutableState<News.Article?>) {
    val tabState = remember { mutableStateOf(TabState.GENERAL) }
    val scroll = rememberLazyListState()
    // Create a new Flow every time tabState changes
    val newsFlow =
        remember(tabState.value) {
            fetchAllHeadlines(source = tabState.value.name.lowercase())
        }

    // Collect it as state for reactive UI
    val uiState = newsFlow.collectAsState(initial = UiState.Loading)
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("News") }) },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            FilterTabs(tabState = tabState, listState = scroll)
            ListBody(uiState = uiState.value, selectedArticle = selectedArticle, listState = scroll)
        }
    }
}
