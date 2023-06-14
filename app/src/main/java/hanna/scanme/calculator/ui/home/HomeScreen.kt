package hanna.scanme.calculator.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import hanna.scanme.calculator.BuildConfig
import hanna.scanme.calculator.domain.model.FunctionFlavor
import hanna.scanme.calculator.domain.model.Routes
import hanna.scanme.calculator.ui.theme.ScanMeCalculatorTheme
import hanna.scanme.calculator.ui.viewmodel.ImageViewModel

@Composable
fun Home(
    navigation: NavController,
    imageViewModel: ImageViewModel
) {
    val flavor = BuildConfig.FLAVOR_function
    val scanResult = imageViewModel.scanResult.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(start = 24.dp))

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Input: ${scanResult.value.mathArgument}")
            Text(text = "Result = ${scanResult.value.result}")
        }

        Button(
            onClick = {
                if (flavor.equals(FunctionFlavor.CAMERA.name, ignoreCase = true)) {
                    navigation.navigate(Routes.CAMERA_SCREEN)
                } else if (flavor.equals(FunctionFlavor.GALLERY.name, ignoreCase = true)) {
                    navigation.navigate(Routes.GALLERY_SCREEN)
                }
            }
        ) {
            Text(text = "Add Input")
        }

        Spacer(modifier = Modifier.padding(start = 24.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScanMeCalculatorTheme {
        Home(
            rememberNavController(),
            ImageViewModel()
        )
    }
}