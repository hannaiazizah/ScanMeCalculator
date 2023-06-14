package hanna.scanme.calculator.ui.preview

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import hanna.scanme.calculator.domain.model.Routes
import hanna.scanme.calculator.ui.viewmodel.ImageViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PreviewScreen(
    imageViewModel: ImageViewModel,
    navController: NavController
) {
    val capturedImage by imageViewModel.capturedImage.collectAsState()
    val previewViewModel: PreviewViewModel = viewModel()
    val context = LocalContext.current

    LaunchedEffect(capturedImage) {
        if (capturedImage != null) {
            previewViewModel.convertImageToText(capturedImage!!)
        }

        previewViewModel.mathArgument.collectLatest { result ->
            if (result.isSuccess) {
                result.getOrNull()?.let { mathArgument ->
                    imageViewModel.calculate(mathArgument)
                    navController.navigate(Routes.HOME_SCREEN)
                }
            } else {
                val error = result.exceptionOrNull()?.message ?: "Unknown error"
                Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        if (capturedImage != null) {
            Image(
                bitmap = capturedImage!!.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "Loading...",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1
            )
        }

        Button(
            onClick = {
                // Clear the captured image and navigate back to the camera screen
                imageViewModel.setCapturedImage(null)
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Retake")
        }
    }
}