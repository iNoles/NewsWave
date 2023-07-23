package com.jonathansteele.news.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jonathansteele.news.MainView

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "News"
    ) {
        MainView()
    }
}