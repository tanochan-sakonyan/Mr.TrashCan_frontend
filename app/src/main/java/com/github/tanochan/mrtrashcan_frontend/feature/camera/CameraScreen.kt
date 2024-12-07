package com.github.tanochan.mrtrashcan_frontend.feature.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import coil.compose.rememberAsyncImagePainter
import java.io.File

@Composable
fun CameraScreenHost(
    navigateToRegister: () -> Unit,
) {
    CameraScreen(
        onBack = navigateToRegister
    )
}

@Composable
fun CameraScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<String?>(null) }
    var hasCameraPermission by remember {
        mutableStateOf (
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PermissionChecker.PERMISSION_GRANTED
        )
    }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
        if (!isGranted) {
            Toast.makeText(context, "カメラの権限が必要です", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(hasCameraPermission) {
        if(!hasCameraPermission) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (!hasCameraPermission) return

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CameraPreview(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onImageCaptured = { photoUri = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onBack) {
                Text("戻る")
            }

            Spacer(modifier = Modifier.height(16.dp))

            photoUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Captured photo",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    onImageCaptured: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { androidx.camera.view.PreviewView(context) }

    AndroidView(
        factory = { previewView },
        modifier = modifier
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = androidx.camera.core.Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val imageCapture = ImageCapture.Builder().build()
            val cameraSelector = androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview, imageCapture
                )
                val photoFile = File(
                    context.externalCacheDir,
                    "IMG_${System.currentTimeMillis()}.jpg"
                )

                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()


                imageCapture?.takePicture(
                    outputOptions,
                    ContextCompat.getMainExecutor(context),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onError(exception: ImageCaptureException) {
                            Toast.makeText(context, "写真の保存に失敗しました", Toast.LENGTH_SHORT).show()
                        }

                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            Toast.makeText(context, "写真を保存しました: ${photoFile.absolutePath}", Toast.LENGTH_SHORT).show()
                            onImageCaptured(photoFile.absolutePath)
                        }
                    }
                )

            } catch (exc: Exception) {
                Toast.makeText(context, "カメラの初期化に失敗しました", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(context))
    }
}