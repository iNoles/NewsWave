package com.jonathansteele.news

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow

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
                val window = UIApplication.sharedApplication.windows.last() as? UIWindow
                val currentViewController = window?.rootViewController
                val activityViewController =
                    UIActivityViewController(
                        activityItems = listOf(article.url, article.title),
                        applicationActivities = null,
                    )
                currentViewController?.presentViewController(
                    viewControllerToPresent = activityViewController,
                    animated = true,
                    completion = null,
                )
            },
    )
}
