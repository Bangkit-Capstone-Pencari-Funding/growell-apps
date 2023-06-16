package com.growell.ui.screens

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.DashPathEffect
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle.Companion.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryScreen(navController: NavController, currentRoute: String) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Pilih Anak") }
    var selectedChildId by remember { mutableStateOf("") }
    var diaryData by remember { mutableStateOf<List<GetDiary>>(emptyList()) }

    var caloriesNeeded by remember { mutableStateOf("") }

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    var mDate = remember { mutableStateOf("") }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    mDate.value = dateFormat.format(mCalendar.time)

    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mYear-${mMonth + 1}-$mDayOfMonth"
        }, mYear, mMonth, mDay
    )

    val savedToken = SharedPrefsUtil.getToken(context)
    var children by remember { mutableStateOf<List<Children>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = ApiClient.getProfile(savedToken)
            if (response.isSuccessful) {
                val profileFromResponse = response.body()
                Log.d("Profile", "Profile: $profileFromResponse")
                children = profileFromResponse?.payload?.result?.children!!
            } else {
                // Handle error response
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    var remainingCalories by remember { mutableStateOf(0) }
    LaunchedEffect(diaryData) {
        remainingCalories = if (caloriesNeeded.isNotEmpty()) {
            val calories = caloriesNeeded.toIntOrNull()
            if (calories != null) {
                val totalCalories = calculateTotalCalories(diaryData)
                val remaining = calories - totalCalories
                if (remaining > 0) {
                    remaining
                } else {
                    0
                }
            } else {
                0
            }
        } else {
            0
        }
    }
//    LaunchedEffect(diaryData) {
//        remainingCalories = if (caloriesNeeded.isNotEmpty()) {
//            val calories = caloriesNeeded.toIntOrNull()
//            if (calories != null) {
//                calories - calculateTotalCalories(diaryData)
//            } else {
//                0
//            }
//        } else {
//            0
//        }
//    }


    GrowellTheme(darkTheme = false) {
        Scaffold(
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(24.dp, 0.dp, 24.dp, 0.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start

                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { mDatePickerDialog.show() }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_left_gray),
                                    contentDescription = "Previous",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = mDate.value,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_right_gray),
                                    contentDescription = "Next",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF029094), shape = RoundedCornerShape(10.dp))
                            .clickable { expanded = !expanded }
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedOption,
                                color = Color.White,
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .clickable { expanded = !expanded }
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_bottom_icon),
                                contentDescription = "Dropdown",
                                tint = Color.White
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        children.forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOption = item.name
                                    selectedChildId = item.id
                                    caloriesNeeded = item.calories_needed.toString()
                                    expanded = false
                                }
                            ) {
                                Text(
                                    text = item.name,
                                    color = Color.Black,
                                    fontFamily = FontFamily.Default,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 24.dp))
                    Text(
                        "Calories",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Poppins
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                            .shadow(
                                elevation = 30.dp,
                                shape = RoundedCornerShape(10.dp),
                                ambientColor = Color.Black.copy(alpha = 0.1f),
                            )

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, RoundedCornerShape(10.dp))
                                .padding(24.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = caloriesNeeded.ifEmpty { "0" },
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .background(
                                            Color(0xFFEEEEEE),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(8.dp)
                                )
                                Text(
                                    text = "Goal",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                )
                            }
                            Column(verticalArrangement = Arrangement.Top) {
                                Text(
                                    text = "-",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = calculateTotalCalories(diaryData).toString(),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .background(
                                            Color(0xFFEEEEEE),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(8.dp)
                                )
                                Text(
                                    text = "Food",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                )
                            }
                            Column(verticalArrangement = Arrangement.Center) {
                                Text(
                                    text = "=",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = remainingCalories.toString(),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .background(
                                            Color(0xFFEEEEEE),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(8.dp)
                                )
                                Text(
                                    text = "Remaining",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                            .shadow(
                                elevation = 30.dp,
                                shape = RoundedCornerShape(10.dp),
                                ambientColor = Color.Black.copy(alpha = 0.1f),
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(10.dp))
                                .padding(24.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total Food",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Poppins,
                                )
                                Text(
                                    text = calculateTotalCalories(diaryData).toString() + " Kkal",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Poppins,
                                    color = Color(0xFF43ADA6)
                                )
                            }
                            Divider(
                                color = Color(0xFFCACACA),
                                thickness = 1.dp,
                                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                            )
                            Text(
                                text = "Food list",
                                fontSize = 10.sp,
                                fontFamily = Poppins,
                            )
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            if (diaryData.isEmpty()) {
                                Text(
                                    text = "Belum makan",
                                    fontSize = 10.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFF666666)
                                )
                            } else {
                                diaryData.map { diary ->
                                    diary.recipe.map {recipe ->
                                        val totalCalories = recipe.ingredients.sumBy { it.calories }
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = recipe.name,
                                                modifier = Modifier.align(Alignment.CenterVertically),
                                                fontSize = 10.sp,
                                                fontFamily = Poppins,
                                                color = Color(0xFF666666)
                                            )
                                            Text(
                                                text = totalCalories.toString(),
                                                modifier = Modifier.align(Alignment.CenterVertically),
                                                fontSize = 10.sp,
                                                fontFamily = Poppins,
                                                color = Color(0xFF666666)
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.padding(top = 8.dp))

                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 24.dp))
                    Button(
                        onClick = {
                            getDiary(selectedChildId,
                                mDate.value,
                                "Bearer $savedToken",
                                onSuccess = {
                                    Toast.makeText(
                                        context,
                                        "Menampilkan diary $selectedOption",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    diaryData = it
                                },
                                onFailure = {
                                    Toast.makeText(
                                        context,
                                        "Gagal menampilkan diary $selectedOption",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6))
                    ) {
                        Text(
                            text = "Get Diary",
                            color = Color.White
                        )
                    }
                }
            },
            bottomBar = {
                Box {
                    BottomNavigation(
                        backgroundColor = Color.White,
                        contentColor = Color.Black,
                        elevation = 8.dp
                    ) {
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.home_icon),
                                    contentDescription = null,
                                    tint = if (currentRoute == "home_screen") Color(0xFF43ADA6) else Color.Gray
                                )
                            },
                            selected = currentRoute == "home_screen",
                            onClick = {
                                navController.navigate("home_screen") {
                                    popUpTo(navController.graph.startDestinationId)
                                }
                            }
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.article_icon),
                                    contentDescription = null
                                )
                            },
                            selected = false,
                            onClick = {}
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = null,
                                    tint = Color.Transparent
                                )
                            },
                            selected = false,
                            enabled = false,
                            onClick = {},
                            alwaysShowLabel = false,
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.diary_icon),
                                    contentDescription = null,
                                    tint = if (currentRoute == "diary_screen") Color(0xFF43ADA6) else Color.Gray
                                )
                            },
                            selected = currentRoute == "diary_screen",
                            onClick = {
                                navController.navigate("diary_screen") {
                                    popUpTo(navController.graph.startDestinationId)
                                }
                            }
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.profile_icon),
                                    contentDescription = null,
                                    tint = if (currentRoute == "profile_screen") Color(0xFF43ADA6) else Color.Gray
                                )
                            },
                            selected = currentRoute == "profile_screen",
                            onClick = {
                                navController.navigate("profile_screen") {
                                    popUpTo(navController.graph.startDestinationId)
                                }
                            }
                        )
                    }
                    FloatingActionButton(
                        onClick = {},
                        backgroundColor = Color(0xFF43ADA6),
                        contentColor = Color.White,
                        elevation = FloatingActionButtonDefaults.elevation(8.dp),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = (-30).dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.scan_icon),
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

fun getDiary(
    childId: String,
    date: String,
    token: String,
    onSuccess: (List<GetDiary>) -> Unit,
    onFailure: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ApiClient.apiService.getDiaryData(childId, date, token)
            if (response.isSuccessful) {
                val diaryResponse = response.body()
                val diaryData = diaryResponse?.payload?.result
                withContext(Dispatchers.Main) {
                    if (diaryData != null) {
                        onSuccess(diaryData)
                    }
                }
                Log.d("GetDiary", "Diary: $diaryData")

            } else {
                withContext(Dispatchers.Main) {
                    Log.d("GetDiary", "GetDiary gagal: ${response.errorBody()?.string()}")
                    onFailure()
                }
            }

            // Lakukan sesuatu dengan token, seperti menyimpannya di Preferences
            // atau melanjutkan ke halaman berikutnya.

        } catch (e: Exception) {
            // Tangani kesalahan, misalnya menampilkan pesan kesalahan kepada pengguna.
            withContext(Dispatchers.Main) {
                // Contoh: Menampilkan pesan kesalahan menggunakan Toast
                Log.d("login", "Login gagal ${e.message}")
            }
        }
    }
}

fun calculateTotalCalories(diaryData: List<GetDiary>): Int {
    var totalCalories = 0

    for (getDiary in diaryData) {
        val getRecipeList = getDiary.recipe

        for (getRecipe in getRecipeList) {
            val ingredientsList = getRecipe.ingredients

            for (ingredient in ingredientsList) {
                totalCalories += ingredient.calories
            }
        }
    }

    return totalCalories
}

