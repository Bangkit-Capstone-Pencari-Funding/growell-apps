package com.growell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.growell.data.SharedPrefsUtil
import com.growell.ui.screens.HomeScreen
import com.growell.ui.screens.SignInScreen
import com.growell.ui.screens.SplashScreen
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.*
import com.growell.ui.screens.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("login_screen") {
            SignInScreen(navController)
        }
        composable("register_screen") {
            SignUpScreen(navController)
        }
    }
}