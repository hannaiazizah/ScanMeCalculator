package hanna.scanme.calculator.ui.preview

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import hanna.scanme.calculator.domain.model.MathArgument
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class PreviewViewModel(
): ViewModel() {

    private val _mathArgument = MutableStateFlow<Result<MathArgument?>>(Result.success(null))
    val mathArgument: StateFlow<Result<MathArgument?>> = _mathArgument

    fun convertImageToText(bitmap: Bitmap) {
        viewModelScope.launch {
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            Log.d("PreviewViewModel", "convertImageToText")
            val textFlow = convert(inputImage)
            textFlow.collect {
                if (it != null) {
                    Log.d("PreviewViewModel", "Text: ${it.text}")
                    val mathArgument = extractMathOperation(it.text)
                    if (mathArgument != null) {
                        _mathArgument.value = Result.success(mathArgument)
                    } else {
                        _mathArgument.value = Result.failure(Exception("Failed to extract math operation. Please Retake!"))
                    }
                } else {
                    _mathArgument.value = Result.failure(Exception("Failed to convert image to text. Please Retake!"))
                }
            }
        }

    }

    private fun extractMathOperation(text: String): MathArgument? {
        val pattern = Regex("""(\d+)\s*([-+*/])\s*(\d+)""")
        val matchResult = pattern.find(text)

        return matchResult?.let {
            val (arg1, operator, arg2) = matchResult.destructured
            val num1 = arg1.toInt()
            val num2 = arg2.toInt()

            MathArgument(
                firstInt = num1,
                operator = operator.first(),
                secondInt = num2
            )
        }
    }

    private suspend fun convert(input: InputImage): Flow<Text?> {
        return callbackFlow {
            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            recognizer.process(input)
                .addOnSuccessListener { texts ->
                    trySend(texts)
                }
                .addOnFailureListener { // Task failed with an exception
                    trySend(null)
                }

            awaitClose { recognizer.close() }
        }
    }
}