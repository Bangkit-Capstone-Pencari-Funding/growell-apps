package com.growell.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.Children
import com.growell.model.ProfileResponse
import com.growell.ui.components.ChildCard
import com.growell.ui.theme.GrowellTheme

@Composable
fun ProfileChildScreen(navController: NavController) {
    val context = LocalContext.current

    val savedToken = SharedPrefsUtil.getToken(context)
    var children by remember { mutableStateOf<List<Children>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = ApiClient.getProfile(savedToken)
            if (response.isSuccessful) {
                val profileFromResponse = response.body()
                children = profileFromResponse?.payload?.result?.children!!
                Log.d("Child Profile", "Children: $children")
            } else {
                // Handle error response
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    GrowellTheme(darkTheme = false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.padding(bottom = 36.dp))
            Text("Profile Child")
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            LazyColumn() {
                items(children) {
                    ChildCard(
                        childname = it.name,
                        dateOfBirth = it.date_of_birth.substring(0, 10),
                        gender = it.gender,
                        weight = it.weight.toString(),
                        height = it.height.toString(),
                        activity = it.activity.toString()
                    )
                }
            }
            Button(
                onClick = {
                    navController.navigate("add_child_screen") {
                        popUpTo("profile_screen")
                    }
                },
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 24.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)),
            ) {
                Text(
                    text = "Add Child",
                    color = Color.White
                )
            }
        }
    }
}
