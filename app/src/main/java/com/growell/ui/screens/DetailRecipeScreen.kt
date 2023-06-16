package com.growell.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.growell.R
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.*
import com.growell.ui.theme.Poppins

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailRecipeScreen(recipeId: String?, navController: NavController) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var selectedChild by remember { mutableStateOf("") }

    var recipe by remember { mutableStateOf<DetailRecipeResponse?>(null) }

    val savedToken = SharedPrefsUtil.getToken(context)
    var children by remember { mutableStateOf<List<Children>>(emptyList()) }

    var selectedChildId by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            val response = ApiClient.getProfile(savedToken)
            if (response.isSuccessful) {
                val profileFromResponse = response.body()
                children = profileFromResponse?.payload?.result?.children!!
            } else {
                // error response
            }
        } catch (e: Exception) {
            // exception
        }
    }

    LaunchedEffect(Unit) {
        try {
            val response = recipeId?.let { ApiClient.apiService.getRecipeDetail(it) }
            if (response != null) {
                if (response.isSuccessful) {
                    val recipeDetail = response.body()
                    if (recipeDetail != null) {
                        recipe = recipeDetail
                    }
                } else {
                    // error response
                }
            }
        } catch (e: Exception) {
            // exception
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GlideImage(
            model = recipe?.payload?.result?.picture,
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
                onClick = { },
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pilih Anak",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.padding(bottom = 12.dp))
                    LazyColumn() {
                        items(children) {
                            RadioButtonWithText(
                                text = it.name,
                                isSelected = selectedChild == it.name,
                                onSelected = {
                                    selectedChild = it.name
                                    selectedChildId = it.id
                                }
                            )
                        }
                    }
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
                        onClick = {
                            showDialog = false
                            navController.navigate("success_cook_screen/$recipeId/$selectedChildId")
                        },
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
    onSelected: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable { onSelected() }
            .padding(bottom = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.child_icon),
            contentDescription = null,
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .padding(start = 8.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = Poppins,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        RadioButton(
            selected = isSelected,
            onClick = null, // Untuk mencegah radio button menjadi tidak responsif saat diklik pada Row
        )
    }
}