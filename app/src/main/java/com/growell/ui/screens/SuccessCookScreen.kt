package com.growell.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.growell.R
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.CreateChildRequest
import com.growell.model.DiaryRequest
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SuccessCookScreen(recipeId: String?, childId: String?, navController: NavController) {
    Log.d("SUCCESS SCREEN", "recipe: $recipeId")
    Log.d("SUCCESS SCREEN", "childId: $childId")

    val context = LocalContext.current

    val savedToken = SharedPrefsUtil.getToken(context)

    val (rating, setRating) = remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(R.drawable.success_icon),
            contentDescription = "Success Icon"
        )
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
        Text("Sudah selesai masak?", fontSize = 24.sp, fontFamily = Poppins)
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Text(
            "Mohon berikan rating untuk \n" +
                    "resep ini.",
            fontSize = 16.sp,
            fontFamily = Poppins,
            color = Color(0xFF656565),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(bottom = 36.dp))
        RatingBar(onRatingChanged = { newRating ->
            setRating(newRating)
        })
        Spacer(modifier = Modifier.padding(bottom = 60.dp))
        Button(
            onClick = {
                if (childId != null) {
                    if (recipeId != null) {
                        createDiary(
                            "",
                            "",
                            rating,
                            childId,
                            "Bearer $savedToken",
                            recipeId,
                            onSuccess = {
                                Toast.makeText(context, "Yummy!!", Toast.LENGTH_SHORT).show()
                                navController.navigate("home_screen") {
                                    popUpTo("success_cook_screen")
                                }
                            },
                            onFailure = {
                                Toast.makeText(context, "Gagal submit rating", Toast.LENGTH_SHORT)
                                    .show()
                            })
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6))
        ) {
            Text(
                text = "Submit Rating",
                color = Color.White
            )
        }
    }
}

@Composable
fun RatingBar(onRatingChanged: (Int) -> Unit) {
    var rating by remember { mutableStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Star",
                tint = if (index < rating) Color(0xFFF29727) else Color.Gray,
                modifier = Modifier.clickable {
                    rating = index + 1
                    onRatingChanged(rating)
                }
            )
        }
    }
}

fun createDiary(
    content: String,
    picture: String,
    rating: Int,
    child_id: String,
    token: String,
    recipeId: String,
    onSuccess: () -> Unit,
    onFailure: () -> Unit
) {
    val createDiaryRequest =
        DiaryRequest(content, picture, rating, child_id)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ApiClient.apiService.createDiary(token, recipeId, createDiaryRequest)
            if (response.isSuccessful) {
                val createDiaryResponse = response.body()
                Log.d("Create Diary", ": Berhasil : $createDiaryResponse")
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Log.d("Create Diary", ": Gagal : ${response.errorBody()?.string()}")
                    onFailure()
                }
            }

        } catch (e: Exception) {
            // Tangani kesalahan, misalnya menampilkan pesan kesalahan kepada pengguna.
            withContext(Dispatchers.Main) {
                // Contoh: Menampilkan pesan kesalahan menggunakan Toast
                Log.d("Create Child", ": ${e.message}")
            }
        }
    }
}