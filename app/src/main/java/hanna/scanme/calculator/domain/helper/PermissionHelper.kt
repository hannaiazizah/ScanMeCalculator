package hanna.scanme.calculator.domain.helper

import android.util.Log
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermissionHelper(
    permissionState: PermissionState,
) {
    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = {
            Log.d("SinglePermissionHelper", "permissionNotGrantedContent")
        },
        permissionNotAvailableContent = {
            Log.d("SinglePermissionHelper", "permissionNotAvailableContent")
        }
    ) {
        // permission granted
    }
}