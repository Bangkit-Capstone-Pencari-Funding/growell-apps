package com.growell.ui.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.DetailRecipeResponse
import com.growell.model.Recipe
import com.growell.model.RecipeDetail
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun DetailRecipeScreen(recipeId: String?) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedChild by remember { mutableStateOf("") }

    var recipe by remember { mutableStateOf<DetailRecipeResponse?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = recipeId?.let { ApiClient.apiService.getRecipeDetail(it) }
            Log.d("detail recipe", "detail: ${response?.body()}")
            if (response != null) {
                if (response.isSuccessful) {
                    val recipeDetail = response.body()
                    if (recipeDetail != null) {
                        recipe = recipeDetail
                    }
                } else {
                    // Handle error response
                }
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.asset_recipe1),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
                .aspectRatio(1.5f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 240.dp)
                .verticalScroll(rememberScrollState())
                .align(Alignment.TopStart)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = 200.dp)
                    .padding(0.dp),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight()
                ) {
                    recipe?.payload?.result?.name?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            fontFamily = Poppins
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 30.dp))
                    recipe?.payload?.result?.how_to?.let {
                        Text(
                            it,
                            fontSize = 11.sp, color = Color(0xFF4F4F4F), fontFamily = Poppins,
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 40.dp))
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)) // Warna latar belakang #43ADA6
                    ) {
                        Text(
                            text = "Mulai Masak",
                            color = Color.White
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        ) {
            IconButton(
                onClick = { /* Aksi ketika tombol back ditekan */ },
                modifier = Modifier.background(Color.White, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left_icon),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Pilih Anak")
            },
            text = {
                Column {
                    Text(
                        text = "Pilih salah satu anak:",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    RadioButtonWithText(
                        text = "Anak 1",
                        isSelected = selectedChild == "Anak 1",
                        onSelected = { selectedChild = "Anak 1" }
                    )
                    RadioButtonWithText(
                        text = "Anak 2",
                        isSelected = selectedChild == "Anak 2",
                        onSelected = { selectedChild = "Anak 2" }
                    )
                    // Tambahkan radio button anak lainnya di sini
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { showDialog = false },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "Batal")
                    }
                    TextButton(
                        onClick = { showDialog = false },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "Pilih")
                    }
                }
            }
        )
    }

}

@Composable
fun RadioButtonWithText(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelected() }
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null // Untuk mencegah radio button menjadi tidak responsif saat diklik pada Row
        )
        Icon(
            painter = painterResource(id = R.drawable.star_icon),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}