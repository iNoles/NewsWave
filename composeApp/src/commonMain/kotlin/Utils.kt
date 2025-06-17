package com.jonathansteele.news

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// It seems that compose multiplatform didn't have calculateWindowSizeClass(this)
fun getWidthSizeClass(maxWidth: Dp): WindowWidthSizeClass {
    return when {
        maxWidth < 600.dp -> WindowWidthSizeClass.Compact
        maxWidth < 840.dp -> WindowWidthSizeClass.Medium
        else -> WindowWidthSizeClass.Expanded
    }
}
