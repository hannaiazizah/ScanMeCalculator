package hanna.scanme.calculator.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hanna.scanme.calculator.domain.model.Routes
import hanna.scanme.calculator.ui.camera.CameraScreen
import hanna.scanme.calculator.ui.viewmodel.ImageViewModel
import hanna.scanme.calculator.ui.gallery.GalleryCapture
import hanna.scanme.calculator.ui.home.Home
import hanna.scanme.calculator.ui.preview.PreviewScreen

@Composable
fun ScanMeApp() {
    val imageViewModel: ImageViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) {
            Home(
                navigation = navController,
                imageViewModel = imageViewModel
            )
        }
        composable(Routes.CAMERA_SCREEN) {
            CameraScreen(
                navigation = navController,
                viewModel = imageViewModel
            )
        }
        composable(Routes.GALLERY_SCREEN) {
            GalleryCapture(
                navigation = navController,
                imageViewModel = imageViewModel
            )
        }
        composable(Routes.PREVIEW_SCREEN) {
            PreviewScreen(
                navController = navController,
                imageViewModel = imageViewModel
            )
        }
    }

}