package com.growell.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.growell.R
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.DetailRecipeResponse
import com.growell.model.ProfileResponse
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun ProfileScreen(navController: NavController, currentRoute: String) {
    val context = LocalContext.current

    val savedToken = SharedPrefsUtil.getToken(context)
    var profile by remember { mutableStateOf<ProfileResponse?>(null) }

    DisposableEffect(Unit) {
        if (savedToken.isNullOrEmpty()) {
            navController.navigate("login_screen") {
                popUpTo("home_screen") { inclusive = true }
            }
        }
        onDispose { }
    }

    LaunchedEffect(Unit) {
        try {
            val response = ApiClient.getProfile(savedToken)
            if (response.isSuccessful) {
                val profileFromResponse = response.body()
                Log.d("Profile", "Profile: $profileFromResponse")
                profile = profileFromResponse
            } else {
                // Handle error response
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    GrowellTheme(darkTheme = false) {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .padding(it)
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Profile",
                        color = Color(0xFF505357),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        fontFamily = Poppins
                    )
                    Spacer(modifier = Modifier.padding(bottom = 34.dp))
                    Image(
                        painter = painterResource(R.drawable.photo_profile),
                        contentDescription = "profile_photo"
                    )
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    profile?.payload?.result?.name?.let { it1 ->
                        Text(
                            it1,
                            color = Color(0xFF505357),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = Poppins
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 2.dp))
                    profile?.payload?.result?.email?.let { it1 ->
                        Text(
                            it1,
                            color = Color(0xFFADB5BD),
                            fontSize = 14.sp,
                            fontFamily = Poppins
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 32.dp))
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 32.dp, vertical = 16.dp)
                            .clickable {
                                navController.navigate("edit_profile_screen")
                            }) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.edit_profile_icon),
                                    contentDescription = "edit_profile_icon",
                                )
                                Text(
                                    "Edit Profile",
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = Poppins,
                                    color = Color(0xFF505357)
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.arrow_right_gray),
                                contentDescription = "arrow_right"
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Divider()
                    }

                    Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.profile_child_icon),
                                    contentDescription = "edit_profile_icon",
                                )
                                Text(
                                    "Profile Child",
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = Poppins,
                                    color = Color(0xFF505357)
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.arrow_right_gray),
                                contentDescription = "arrow_right"
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Divider()
                    }

                    Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.about_icon),
                                    contentDescription = "edit_profile_icon",
                                )
                                Text(
                                    "About",
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = Poppins,
                                    color = Color(0xFF505357)
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.arrow_right_gray),
                                contentDescription = "arrow_right"
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Divider()
                    }

                    Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.help_center_icon),
                                    contentDescription = "edit_profile_icon",
                                )
                                Text(
                                    "Help Center",
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = Poppins,
                                    color = Color(0xFF505357)
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.arrow_right_gray),
                                contentDescription = "arrow_right"
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Divider()
                    }

                    Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable {
                                    SharedPrefsUtil.clearToken(context)
                                    navController.navigate("login_screen") {
                                        popUpTo("login_screen") { inclusive = true }
                                    }
                                }) {
                                Image(
                                    painter = painterResource(R.drawable.logout_icon),
                                    contentDescription = "edit_profile_icon",
                                )
                                Text(
                                    "Log Out",
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = Poppins,
                                    color = Color(0xFFE5383B)
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.arrow_right_gray),
                                contentDescription = "arrow_right"
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Divider()
                    }
                    Button(
                        onClick = {
                            navController.navigate("add_child_screen")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6))
                    ) {
                        Text(
                            text = "Add Child",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 100.dp))
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