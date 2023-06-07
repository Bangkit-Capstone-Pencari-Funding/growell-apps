package com.growell.ui.screens

import android.widget.ScrollView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.ui.components.RecipeListItem
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun HomeScreen() {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.avatar_example),
                        contentDescription = "profile_picture",
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(35.dp)
                            .height(35.dp)
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "icon_notification"
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    "Good to see you,",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Poppins,
                    color = Color(0xFF371B34)
                )
                Text(
                    "Sarina!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    color = Color(0xFF371B34)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFD9FFEA), shape = RoundedCornerShape(20.dp))
                        .padding(21.dp, 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(
                            "Check Your Food",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF43ADA6),
                            fontFamily = Poppins
                        )
                        Text(
                            "Let's check the quality of our\nhealthy food",
                            fontSize = 12.sp,
                            color = Color(0xFF43ADA6),
                            fontFamily = Poppins
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Scan Now",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF43ADA6),
                                fontFamily = Poppins
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Image(
                                painter = painterResource(R.drawable.scan_filled_icon),
                                contentDescription = "scan_icon_filled",
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                        }

                    }
                    Image(
                        painter = painterResource(R.drawable.meetup_icon),
                        contentDescription = "scanner_banner",
                        modifier = Modifier
                            .width(80.dp)
                            .height(70.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Text(
                    "Favorites Recipe",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color(0xFF323643),
                    fontFamily = Poppins
                )
                LazyRow {
                    item { RecipeListItem() }
                    item { RecipeListItem() }
                    item { RecipeListItem() }
                }
                Spacer(modifier = Modifier.padding(25.dp))
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
                        icon = { Icon(painter = painterResource(R.drawable.home_icon), contentDescription = null) },
                        selected = false,
                        onClick = {}
                    )
                    BottomNavigationItem(
                        icon = { Icon(painter = painterResource(R.drawable.article_icon), contentDescription = null) },
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
                        alwaysShowLabel = false, // Hides label
                    )
                    BottomNavigationItem(
                        icon = { Icon(painter = painterResource(R.drawable.diary_icon), contentDescription = null) },
                        selected = false,
                        onClick = {}
                    )
                    BottomNavigationItem(
                        icon = { Icon(painter = painterResource(R.drawable.profile_icon), contentDescription = null) },
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
                    Icon(painter = painterResource(R.drawable.scan_icon), contentDescription = null)
                }
            }
        }
    )
}


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun HomeScreenPreview() {
    GrowellTheme {
        HomeScreen()
    }
}