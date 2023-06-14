package hanna.scanme.calculator.ui.camera

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import hanna.scanme.calculator.domain.helper.executor
import hanna.scanme.calculator.domain.helper.toBitmap
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ImageCaptureButton(
    imageCapture: ImageCapture?,
    onImageCaptured: (Bitmap) -> Unit
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val outputFile = createOutputFile()
            val outputOptions = ImageCapture.OutputFileOptions
                .Builder(
                    context.contentResolver,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    outputFile
                )
                .build()

            imageCapture?.takePicture(
                outputOptions,
                context.executor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        outputFileResults.savedUri?.toBitmap(context = context)?.let {
                            onImageCaptured(it)
                        }
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.d("ImageCaptureButton", "onError: ${exception.message}", exception)
                    }
                }
            )
        }
    ) {
        Text(text = "Capture")
    }
}

private fun createOutputFile(): ContentValues {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, timeStamp)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }
    }
    return contentValues
}