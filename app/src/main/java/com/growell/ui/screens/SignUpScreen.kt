package com.growell.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.growell.R
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.LoginRequest
import com.growell.model.RegisterRequest
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Hey, There",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins,
                color = Color(0xFF343434)
            )
            Text(
                "Create an account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                color = Color(0xFF343434)
            )
        }
        Column(modifier = Modifier.padding(horizontal = 40.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter Your name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
//            keyboardOptions = KeyboardOptions(email = true),
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter Your Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Enter Your Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = { Text("Enter Your Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 40.dp))
            Button(
                onClick = {
                    performRegister(name, phone, email, password,
                        onSuccess = { token ->
                            SharedPrefsUtil.saveToken(context, token)
                            navController.navigate("home_screen")
                        },
                        onFailure = {
                            Toast.makeText(context, "Register Gagal", Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)) // Warna latar belakang #43ADA6
            ) {
                Text(
                    text = "Register",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Or With",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
                Divider(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 40.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.google_logo),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Sign up with Google")
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 35.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    "already have an account ? ",
                    color = Color(0xFF0D0E0E),
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                Text(
                    "Sign In",
                    color = Color(0xFF43ADA6),
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate("login_screen")
                    }
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
        }
    }
}

fun performRegister(
    name: String,
    phone: String,
    email: String,
    password: String,
    onSuccess: (String) -> Unit,
    onFailure: () -> Unit
) {
    val registerRequest = RegisterRequest(name, phone, email, password)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ApiClient.apiService.register(registerRequest)
            if (response.isSuccessful) {
                val registerResponse = response.body()
                val token = registerResponse?.payload?.token
                withContext(Dispatchers.Main) {
                    onSuccess(token ?: "")
                }
                Log.d("register", "Register berhasil $token")

            } else {
                withContext(Dispatchers.Main) {
                    Log.d("register", "Register gagal: ${response.errorBody()?.string()}")
                    onFailure()
                }
            }

            // Lakukan sesuatu dengan token, seperti menyimpannya di Preferences
            // atau melanjutkan ke halaman berikutnya.

        } catch (e: Exception) {
            // Tangani kesalahan, misalnya menampilkan pesan kesalahan kepada pengguna.
            withContext(Dispatchers.Main) {
                // Contoh: Menampilkan pesan kesalahan menggunakan Toast
                Log.d("login", "Login gagal ${e.message}")
            }
        }
    }
}