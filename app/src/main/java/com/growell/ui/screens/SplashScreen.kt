package com.growell.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.growell.R
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController?, modifier: Modifier = Modifier) {
    LaunchedEffect(key1 = true) {
        delay(5000L)
        navController?.navigate(
            route = "home_screen",
            navOptions = NavOptions.Builder().setPopUpTo("splash_screen", inclusive = true).build()
        )
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "growell_logo",
            modifier = Modifier
                .width(80.dp)
                .height(62.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "GroWell",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF43ADA6),
            fontFamily = Poppins
        )
    }
}


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun SplashScreenPreview() {
    GrowellTheme {
        SplashScreen(navController = null, modifier = Modifier.fillMaxSize())
    }
}