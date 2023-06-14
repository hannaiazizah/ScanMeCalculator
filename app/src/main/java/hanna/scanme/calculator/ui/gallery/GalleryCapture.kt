package hanna.scanme.calculator.ui.gallery

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hanna.scanme.calculator.domain.model.Routes
import hanna.scanme.calculator.ui.viewmodel.ImageViewModel

@Composable
fun GalleryCapture(
    navigation: NavController,
    imageViewModel: ImageViewModel
) {
    val context = LocalContext.current
    val selectedImage = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            // Handle the selected image here
            selectedImage.value = uri?.let { loadBitmapFromUri(context, it) }
        }
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Open Gallery")
        }

        Button(
            onClick = {
                imageViewModel.setCapturedImage(selectedImage.value)
                navigation.navigate(Routes.PREVIEW_SCREEN)
            },
            enabled = selectedImage.value != null
        ) {
            Text(text = "Process Image")
        }

        selectedImage.value?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected Image",
                modifier = Modifier.padding(top = 16.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

// Helper function to load bitmap from URI
private fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

}