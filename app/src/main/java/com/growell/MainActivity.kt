package com.growell

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
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
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import com.growell.ui.screens.*

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {

    val context = LocalContext.current
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
        composable("add_child_screen") {
            AddChildScreen(navController)
        }
        composable("profile_child_screen") {
            ProfileChildScreen(navController)
        }
        composable("detail_recipe_screen/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            DetailRecipeScreen(recipeId = recipeId, navController)
        }
        composable("success_cook_screen/{recipeId}/{childId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            val childId = backStackEntry.arguments?.getString("childId")
            SuccessCookScreen(recipeId = recipeId, childId = childId, navController)
        }
        composable("take_picture_screen") {
            TakePictureScreen(navController)
        }

        composable("list_recipe_screen/{ingredients}") { backStackEntry ->
            val ingredient = backStackEntry.arguments?.getString("ingredients")
            ListRecipeScreen(ingredient = ingredient, navController)
        }
    }

//    LaunchedEffect(key1 = navController) {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.route == "take_picture_screen") {
//                if (hasCameraPermission(context)) {
//                    openCamera(activityResultLauncher)
//                } else {
//                    requestCameraPermission(permissionLauncher)
//                }
//            }
//        }
//    }
}



