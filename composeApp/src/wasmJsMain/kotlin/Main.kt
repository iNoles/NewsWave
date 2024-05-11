import androidx.compose.ui.window.CanvasBasedWindow

fun main() {
    CanvasBasedWindow(canvasElementId = "compose-target") {
        App()
    }
}
