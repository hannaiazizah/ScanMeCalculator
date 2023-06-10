package hanna.scanme.calculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import hanna.scanme.calculator.BuildConfig

private val DarkColorPalette = darkColors(
    primary = Purple200, primaryVariant = Purple700, secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500, primaryVariant = Purple700, secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val GreenColorPalette = lightColors(
    primary = Green200,
    primaryVariant = Green700,
    secondary = DeepOrange200,
    secondaryVariant = DeepOrange500,
)

private val RedColorPalette = lightColors(
    primary = Red500,
    primaryVariant = Red700,
    secondary = LightBlue200,
    secondaryVariant = LightBlue500
)

@Composable
fun ScanMeCalculatorTheme(
    flavor: String = BuildConfig.FLAVOR,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when  {
        flavor.contains("red") -> RedColorPalette
        flavor.contains("green") -> GreenColorPalette
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colors = colors, typography = Typography, shapes = Shapes, content = content
    )
}