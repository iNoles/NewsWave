package com.jonathansteele.news

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun ShareURL(
    article: News.Article,
    platformContext: PlatformContext,
) {
    Icon(
        imageVector = Icons.Default.Share,
        contentDescription = "Share",
        modifier =
            Modifier.clickable {
                val shareIntent: Intent =
                    Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_SUBJECT, article.title)
                        putExtra(Intent.EXTRA_TEXT, article.url)
                    }
                platformContext.androidContext.startActivity(Intent.createChooser(shareIntent, null))
            },
    )
}
