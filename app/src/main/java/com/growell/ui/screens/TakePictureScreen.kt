package com.growell.ui.screens

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.growell.R
import com.growell.api.ApiClient2
import com.growell.ui.theme.Poppins
import com.growell.util.StorageFileUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

var globalUri: Uri? = null

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TakePictureScreen(navController: NavController) {
    val context = LocalContext.current
    val imageUriState = remember { mutableStateOf<Uri?>(null) }
    var detectedIngredient by remember { mutableStateOf<List<String>>(emptyList()) }
    var inputIngredient by remember { mutableStateOf("") }

    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        val result = activityResult.data
        if (activityResult.resultCode == Activity.RESULT_OK && result != null) {
            imageUriState.value = result.data
//            imageUriState.value = globalUri
            Toast.makeText(context, "${imageUriState.value}", Toast.LENGTH_SHORT).show()
        }
    }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUriState.value = uri
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openCamera(context, activityResultLauncher)
        } else {
            // Izin ditolak, lakukan penanganan yang sesuai
        }
    }

    val storagePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openGallery(pickImageLauncher)
        } else {
            // Permission denied, handle accordingly
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.padding(top = 24.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            imageUriState.value?.let { imageUri ->
                GlideImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )
            } ?: run {
                Image(
                    painter = painterResource(R.drawable.restaurant),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        Button(
            onClick = {
                if (hasCameraPermission(context)) {
                    openCamera(context, activityResultLauncher)
                } else {
                    requestCameraPermission(permissionLauncher)
                }
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, Color(0xFF43ADA6))
        ) {
            Text(
                text = "Ambil Gambar",
                color = Color(0xFF7D7D7D),
                fontSize = 10.sp,
                fontFamily = Poppins
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 5.dp))

        Button(
            onClick = {
                if (hasStoragePermission(context)) {
                    openGallery(pickImageLauncher)
                } else {
                    requestStoragePermission(storagePermissionLauncher)
                }
            }, colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, Color(0xFF43ADA6))
        ) {
            Text(
                text = "Buka Gallery",
                color = Color(0xFF7D7D7D),
                fontSize = 10.sp,
                fontFamily = Poppins
            )
        }

//        imageUriState.value?.let { imageUri ->
//            GlideImage(
//                model = imageUri,
//                contentDescription = null,
//                modifier = Modifier
//                    .width(400.dp)
//                    .height(400.dp),
//                contentScale = ContentScale.Crop
//            )
//        }


        OutlinedTextField(
            value = inputIngredient,
            onValueChange = { inputIngredient = it },
            label = { Text("ingridients") },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
//            keyboardOptions = KeyboardOptions(email = true),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            )
        )

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        Button(
            onClick = {
                submitImageToAnalyze(imageUriState.value, context, onSuccess = {
                    detectedIngredient = it
                    inputIngredient = it.joinToString(", ")
                }, onFailure = {})
            },
            colors = ButtonDefaults.buttonColors(Color(0xff43ADA6)),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(
                text = "Analyze Gambar",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 24.dp))
        Button(
            onClick = {
                navController.navigate("list_recipe_screen/${inputIngredient}")
            },
            colors = ButtonDefaults.buttonColors(Color(0xff43ADA6)),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(
                text = "Tampilkan Rekomendasi Resep",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}

fun submitImageToAnalyze(
    image: Uri?,
    context: Context,
    onSuccess: (List<String>) -> Unit,
    onFailure: () -> Unit
) {

    val file = StorageFileUtil.from(context, image)
    val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
    val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ApiClient2.apiService2.analyzeImage(imagePart)
            if (response.isSuccessful) {
                val loginResponse = response.body()
                val token = loginResponse?.objects
                withContext(Dispatchers.Main) {
                    if (token != null) {
                        onSuccess(token)
                    }
                }

            } else {
                withContext(Dispatchers.Main) {
                    onFailure()
                }
            }


        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
            }
        }
    }
}


private fun hasCameraPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

private fun requestCameraPermission(permissionLauncher: ActivityResultLauncher<String>) {
    permissionLauncher.launch(Manifest.permission.CAMERA)
}

private fun openCamera(context: Context, activityResultLauncher: ActivityResultLauncher<Intent>) {
    val contentValues = ContentValues()
    contentValues.put(MediaStore.Images.Media.TITLE, System.currentTimeMillis().toString())
    globalUri =
        context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, globalUri)
    activityResultLauncher.launch(takePicture)
}

private fun openGallery(pickImageLauncher: ActivityResultLauncher<String>) {
    pickImageLauncher.launch("image/*")
}

private fun hasStoragePermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

private fun requestStoragePermission(permissionLauncher: ActivityResultLauncher<String>) {
    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
}