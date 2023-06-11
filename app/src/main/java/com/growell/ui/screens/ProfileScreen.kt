package com.growell.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun ProfileScreen() {
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
                    Text(
                        "Amelia Andandi",
                        color = Color(0xFF505357),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontFamily = Poppins
                    )
                    Spacer(modifier = Modifier.padding(bottom = 2.dp))
                    Text("amelia@email.com", color = Color(0xFFADB5BD), fontSize = 14.sp, fontFamily = Poppins)
                    Spacer(modifier = Modifier.padding(bottom = 32.dp))
                    Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
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
                        onClick = {},
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)) // Warna latar belakang #43ADA6
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
                                    contentDescription = null
                                )
                            },
                            selected = false,
                            onClick = {}
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
                                    contentDescription = null
                                )
                            },
                            selected = false,
                            onClick = {}
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.profile_icon),
                                    contentDescription = null
                                )
                            },
                            selected = false,
                            onClick = {}
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


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun ProfileScreenPreview() {
    GrowellTheme(darkTheme = false) {
        ProfileScreen()
    }
}