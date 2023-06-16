package com.growell.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Size
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.lifecycle.lifecycleScope
import com.google.common.util.concurrent.ListenableFuture
import com.growell.BuildConfig
import com.growell.R
import com.growell.util.DeleteFileOnExit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraScreen : AppCompatActivity() {
    private val REQUIRED_PERMISSIONS = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private lateinit var preview: Preview
    private lateinit var cameraSelector: CameraSelector
    private lateinit var processCameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var previewView: PreviewView
    private lateinit var btCapture: AppCompatImageView
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private lateinit var outputDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_screen)
        previewView = findViewById(R.id.previewView)
        btCapture = findViewById(R.id.btCapture)
        processCameraProviderFuture = ProcessCameraProvider.getInstance(this)
        outputDirectory = getOutputDirectory(this)
        CameraSelector.LENS_FACING_BACK
        if (allPermissionGranted()) {
            //TODO
        } else {
            callPermissionRequest()
        }
        provideCamera()
    }
    private fun getOutputDirectory(context: Context): File {
        val appContext = context.applicationContext
        val mediaDir = ContextCompat.getExternalCacheDirs(context).firstOrNull().let {
            File(it, "App").apply { mkdirs() }
        }
        return if (mediaDir.exists())
            mediaDir else appContext.filesDir
    }


    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun callPermissionRequest() {
        ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, 666)
    }

    private fun provideCamera() {
        processCameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            processCameraProvider = processCameraProviderFuture.get()
            lifecycleScope.launch(Dispatchers.Main) {
                setupNewCamera()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun setupNewCamera() {
        // Default camera is back
        cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

        processCameraProvider.unbindAll()
        val camera = processCameraProvider.bindToLifecycle(
            this,
            cameraSelector,
            buildPreviewUseCase(),
            buildImageCaptureUseCase()
        )
        setupTapForFocus(camera.cameraControl)
    }

    private fun buildPreviewUseCase(): Preview {
        val display = previewView.display
        preview = Preview.Builder()
            .setTargetRotation(display.rotation)
            .setTargetResolution(
                Size(
                    Resources.getSystem().displayMetrics.widthPixels,
                    Resources.getSystem().displayMetrics.heightPixels
                )
            )
            .build()
            .apply {
                this.setSurfaceProvider(previewView.surfaceProvider)
            }
        preview.setSurfaceProvider(previewView.surfaceProvider)
        return preview
    }
    private fun buildImageCaptureUseCase(): ImageCapture {
        val display = previewView.display
        val imageCapture = ImageCapture.Builder()
            .setTargetRotation(display.rotation)
            .setTargetResolution(
                Size(
                    Resources.getSystem().displayMetrics.widthPixels,
                    Resources.getSystem().displayMetrics.heightPixels
                )
            )
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
            .build()

        // Create output file to hold the image
        val photoFile = createFile(outputDirectory, FILENAME, PHOTO_EXTENSION)

        // Setup image capture metadata
        val metadata = ImageCapture.Metadata().apply {
            // Mirror image when using the front camera (default false because just from back camera)
            isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
            .setMetadata(metadata)
            .build()


        btCapture.setOnClickListener {
            btCapture.isEnabled = false
//            progressDialog.setMessage("Memproses gambar ... ")
//            progressDialog.show()
            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(this),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)
                        val msg = "Photo capture succeeded: ${savedUri.path}"

                        goToNextProcess(savedUri.toFile())
                    }

                    override fun onError(exception: ImageCaptureException) {
                        val msg = "Photo capture failed: ${exception.message}"
                    }
                })
        }

        return imageCapture
    }

    private fun createFile(baseFolder: File, format: String, extension: String) =
        File(
            baseFolder, SimpleDateFormat(format, Locale.getDefault())
                .format(System.currentTimeMillis()) + extension
        )

    private fun goToNextProcess(file: File) {
        lifecycleScope.launch(Dispatchers.Main) {
            // to delete file after activity destroyed
            DeleteFileOnExit.add(file)
            val resultUri = getPhotoFileUri(file)
            val intent = Intent()
            intent.putExtra("result", resultUri)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun getPhotoFileUri(file: File): Uri {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Uri.fromFile(file)
        } else {
            FileProvider.getUriForFile(this, this.packageName, file)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupTapForFocus(cameraControl: CameraControl) {
        previewView.setOnTouchListener { _, event ->
            if (event.action != MotionEvent.ACTION_UP) {
                return@setOnTouchListener true
            }
            val factory = previewView.meteringPointFactory

            val point = factory.createPoint(event.x, event.y)
            val action = FocusMeteringAction.Builder(point).build()
            cameraControl.startFocusAndMetering(action)
            return@setOnTouchListener true
        }
    }
    companion object {
        private const val FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val PHOTO_EXTENSION = ".jpg"
    }
}