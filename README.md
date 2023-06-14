# Scan me! calculator

Scan me! calculator is a simple, yet configurable image-to-result calculator Android application. It allows users to capture arithmetic expressions either directly from the built-in camera or from an image file picked from the filesystem. The app then detects the expression in the picture and computes its result.

## Features

- Capture arithmetic expressions using the built-in camera or select an image file from the filesystem.
- Automatically detect and extract arithmetic expressions from the provided images.
- Compute the result of the detected arithmetic expression.
- Configurable theme options at compile time: red and green.
- Configurable UI functionality options at compile time:
    - Only allow picking pictures from the filesystem.
    - Only allow using the built-in camera.

## Requirements

- Android 7.0 (Nougat) or higher.

## Libraries Used

Scan me! calculator utilizes the following libraries:

- Jetpack Compose UI: A modern and declarative UI toolkit for building Android applications.
- Kotlin Coroutine: A concurrency framework for writing asynchronous and non-blocking code.
- Kotlin Flow: A stream processing library for handling asynchronous data streams.
- CameraX: A Jetpack library for accessing the device camera and capturing images.
- Google ML Kit Vision: A machine learning framework provided by Google for performing image analysis tasks.

## Configuration

To configure the app's theme and UI functionality at compile time, you can adjust the build variant in Android Studio. Follow these steps:

1. Open the project in Android Studio.

2. Open the Build Variants panel. You can find it in the lower-left corner of the Android Studio window. If you don't see it, go to `View` > `Tool Windows` > `Build Variants`.

3. In the Build Variants panel, locate the `app` module. You should see a dropdown menu with available build variants.

4. Select the desired build variant based on your configuration requirements:
    - For the red theme and filesystem image picking functionality, select the `redGallery` variant.
    - For the red theme and built-in camera functionality, select the `redCamera` variant.
    - For the green theme and filesystem image picking functionality, select the `greenGallery` variant.
    - For the green theme and built-in camera functionality, select the `greenCamera` variant.

5. Android Studio will sync the project and apply the selected build variant.

6. Build and run the application on an Android device or emulator to see the configured theme and UI functionality.

Note: If you don't see the desired build variants in the dropdown menu, make sure you have properly configured the flavors and build types in your project's Gradle files.

## Usage

Upon launching the Scan me! calculator app, you will be presented with the main screen. Follow the steps below to utilize the app's features:

1. Click on the "Add Input" button to capture an arithmetic expression.

2. If you choose to use the built-in camera:
    - Grant the app access to your device's camera when prompted.
    - Align the arithmetic expression within the camera frame and capture the image by clicking the capture button.
    - The app will process the image and detect the arithmetic expression.

3. If you prefer to select an image from the gallery:
    - Grant the app access to your device's camera when prompted.
    - Browse through your gallery and select an image containing the arithmetic expression.
    - The app will process the selected image and detect the arithmetic expression.

4. After detecting the expression, the app will compute its result and display it on the screen.

5. You can repeat the process by clicking the "Add Input" button again to capture another arithmetic expression either from the camera or gallery.

Note: For better accuracy, ensure that the captured images or selected gallery images have clear and legible arithmetic expressions.
## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines)
- [Kotlin Flow](https://kotlinlang.org/docs/flow)
- [CameraX](https://developer.android.com/training/camerax)
- [Google ML Kit Vision](https://developers.google.com/ml-kit/vision)

## Contact

For any further questions or inquiries, please contact the me at hannaiazizah@gmail.com.