package hanna.scanme.calculator.ui.camera

import android.Manifest
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import hanna.scanme.calculator.domain.helper.SinglePermissionHelper
import hanna.scanme.calculator.ui.viewmodel.ImageViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(navigation: NavHostController, viewModel: ImageViewModel) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    SinglePermissionHelper(
        permissionState = permissionState
    )
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    if (permissionState.hasPermission) {
        CameraCapture(
            navigation,
            viewModel
        )
    } else {
        Text(text = "Permission not granted")
    }

}




