package hanna.scanme.calculator.ui.camera

import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberCameraSurface(): CameraSurface {
    return remember { CameraSurface() }
}

class CameraSurface {
    var previewView: PreviewView? = null

    val surfaceProvider: Preview.SurfaceProvider
        get() = previewView!!.surfaceProvider

    fun setView(previewView: PreviewView) {
        this.previewView = previewView
    }
}