package com.jonathansteele.news

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.platform.LocalClipboard
import kotlinx.coroutines.launch

@Composable
actual fun ShareURL(article: News.Article) {
    val clipboard: Clipboard = LocalClipboard.current
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    IconButton(onClick = {
        coroutineScope.launch {
            clipboard.setClipEntry(ClipEntry(article.url))
            snackBarHostState.showSnackbar("URL copied to clipboard", duration = SnackbarDuration.Short)
        }
    }) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share URL",
        )
    }

    SnackbarHost(hostState = snackBarHostState)
}
