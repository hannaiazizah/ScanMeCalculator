package hanna.scanme.calculator.ui.camera

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import hanna.scanme.calculator.domain.helper.getCameraProvider
import hanna.scanme.calculator.domain.model.Routes
import hanna.scanme.calculator.ui.viewmodel.ImageViewModel

@Composable
fun CameraCapture(
    navController: NavController,
    viewModel: ImageViewModel
) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val cameraSurface = rememberCameraSurface()
        val imageCaptureUseCase by remember {
            mutableStateOf(
                ImageCapture.Builder().build()
            )
        }
        LaunchedEffect(Unit) {
            cameraSurface.setView(previewView = PreviewView(context))
            val preview = Preview.Builder().build()
                .also {
                    it.setSurfaceProvider(cameraSurface.surfaceProvider)
                }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            val cameraProvider = context.getCameraProvider()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycle,
                    cameraSelector,
                    preview,
                    imageCaptureUseCase
                )
            } catch (exc: Exception) {
                Log.d("CameraCapture", "Use case binding failed", exc)
            }
        }

        cameraSurface.previewView?.let { view ->
            AndroidView(
                factory = {
                    view
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            ImageCaptureButton(
                imageCapture = imageCaptureUseCase,
                onImageCaptured = { bitmap ->
                    viewModel.setCapturedImage(bitmap)
                    navController.navigate(Routes.PREVIEW_SCREEN)
                }
            )
        }
    }
}

