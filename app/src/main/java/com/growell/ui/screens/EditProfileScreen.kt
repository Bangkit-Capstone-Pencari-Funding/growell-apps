package com.growell.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.growell.R
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.LoginRequest
import com.growell.model.ProfileResponse
import com.growell.model.UpdateProfileRequest
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun EditProfileScreen(navController: NavController) {
    val context = LocalContext.current

    val savedToken = SharedPrefsUtil.getToken(context)
    var profile by remember { mutableStateOf<ProfileResponse?>(null) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            val response = ApiClient.getProfile(savedToken)
            if (response.isSuccessful) {
                val profileFromResponse = response.body()
                profile = profileFromResponse
                name = profile?.payload?.result?.name.toString()
                email = profile?.payload?.result?.email.toString()
                phone = profile?.payload?.result?.phone.toString()
            } else {
                // Handle error response
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 36.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(bottom = 46.dp))
        Box() {
            Image(
                painter = painterResource(R.drawable.photo_profile),
                contentDescription = "edit photo"
            )
            Image(
                painter = painterResource(R.drawable.edit_photo_icon),
                contentDescription = "edit photo",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 14.dp))
        Text(
            "Change the profile",
            color = Color(0xFF505357),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            fontFamily = Poppins,
        )
        Column(horizontalAlignment = Start) {
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            // INPUT
            Text(
                "Nama Lengkap",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Tuliskan Nama Lengkap") },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )

            Spacer(modifier = Modifier.padding(bottom = 16.dp))

            Text(
                "Email",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Tuliskan Email Anda") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 16.dp))

            Text(
                "Phone Number",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Tuliskan Nomor Telepon") },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )

            Spacer(modifier = Modifier.padding(bottom = 64.dp))
        }
        Button(
            onClick = {
                if (savedToken != null) {
                    updateProfile(name, phone, "Bearer $savedToken",
                        onSuccess = {
                            navController.navigate("profile_screen")
                            Toast.makeText(context, "Update Profile Berhasil", Toast.LENGTH_SHORT).show()
                        },
                        onFailure = {
                            Toast.makeText(context, "Update Profile Gagal", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp)
        ) {
            Text(
                text = "Save Changes",
                color = Color.White
            )
        }
    }
}

fun updateProfile(
    name: String,
    phone: String,
    token: String,
    onSuccess: () -> Unit,
    onFailure: () -> Unit
) {
    val profileUpdateRequest = UpdateProfileRequest(name, phone)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ApiClient.apiService.updateProfile(token, profileUpdateRequest)
            if (response.isSuccessful) {
                val updateProfileResponse = response.body()
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } else {
                withContext(Dispatchers.Main) {
                    onFailure()
                }
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
            }
        }
    }
}
