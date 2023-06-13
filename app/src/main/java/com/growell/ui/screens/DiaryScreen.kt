package com.growell.ui.screens

import android.graphics.DashPathEffect
import android.graphics.Path
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController

@Composable
fun DiaryScreen(navController: NavController, currentRoute: String) {
    val dropdownItems = listOf("Option 1", "Option 2", "Option 3")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(dropdownItems[0]) }

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
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_left_gray),
                                    contentDescription = "Previous",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = "Yesterday",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
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
                        dropdownItems.forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOption = item
                                    expanded = false
                                }
                            ) {
                                Text(
                                    text = item,
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
                                    text = "1200",
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
                                    text = "330",
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
                                    text = "970",
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
                                    text = "Breakfast",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Poppins,
                                )
                                Text(
                                    text = "330 Kkal",
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Biscuit Regal",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFF666666)
                                )
                                Text(
                                    text = "300",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFF666666)
                                )
                            }
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Bebelac",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFF666666)
                                )
                                Text(
                                    text = "150",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFF666666)
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
                                    text = "Lunch",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = Poppins,
                                )
                                Text(
                                    text = "300 Kkal",
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
                            Spacer(modifier = Modifier.padding(bottom = 8.dp))
                            Button(
                                onClick = {},
                                border = BorderStroke(
                                    1.dp,
                                    color = Color(0xFF029094).copy(alpha = 0.5f)
                                ),
                                colors = ButtonDefaults.buttonColors(Color.White),
                                contentPadding = PaddingValues(vertical = 1.dp, horizontal = 16.dp),
                                content = {
                                    Text(
                                        text = "Add Food",
                                        color = Color(0xFF029094),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 8.sp
                                    )
                                }
                            )

                        }
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