package hanna.scanme.calculator.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import hanna.scanme.calculator.domain.model.MathArgument
import hanna.scanme.calculator.domain.model.ScanResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ImageViewModel : ViewModel() {
    private val _capturedImage = MutableStateFlow<Bitmap?>(null)
    val capturedImage: StateFlow<Bitmap?> = _capturedImage

    private val _scanResult = MutableStateFlow(ScanResult())
    val scanResult: StateFlow<ScanResult> = _scanResult

    fun setCapturedImage(image: Bitmap?) {
        _capturedImage.value = image
    }

    fun calculate(mathArgument: MathArgument) {
        val result = when (mathArgument.operator) {
            '+' -> mathArgument.firstInt + mathArgument.secondInt
            '-' -> mathArgument.firstInt - mathArgument.secondInt
            '*' -> mathArgument.firstInt * mathArgument.secondInt
            '/' -> mathArgument.firstInt / mathArgument.secondInt
            else -> 0
        }

        _scanResult.value = ScanResult(
            mathArgument = "${mathArgument.firstInt} ${mathArgument.operator} ${mathArgument.secondInt}",
            result = result
        )
    }
}