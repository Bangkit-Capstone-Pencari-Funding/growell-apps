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
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.growell.ui.screens.*

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

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable("home_screen") {
            if (currentRoute != null) {
                HomeScreen(navController, currentRoute)
            }
        }
        composable("diary_screen") {
            if (currentRoute != null) {
                DiaryScreen(navController, currentRoute)
            }
        }
        composable("profile_screen") {
            if (currentRoute != null) {
                ProfileScreen(navController, currentRoute)
            }
        }
        composable("login_screen") {
            SignInScreen(navController)
        }
        composable("register_screen") {
            SignUpScreen(navController)
        }
        composable("edit_profile_screen") {
            EditProfileScreen(navController)
        }
        composable("detail_recipe_screen/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            DetailRecipeScreen(recipeId = recipeId)
        }
    }
}