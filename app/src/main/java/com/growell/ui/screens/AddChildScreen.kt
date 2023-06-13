package com.growell.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.growell.api.ApiClient
import com.growell.data.SharedPrefsUtil
import com.growell.model.CreateChildRequest
import com.growell.model.UpdateProfileRequest
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AddChildScreen(navController: NavController) {
    val context = LocalContext.current

    val savedToken = SharedPrefsUtil.getToken(context)

    var childName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var activity by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    var selectedGender by remember { mutableStateOf("") }
    GrowellTheme(darkTheme = false) {
        Column() {
            Column(horizontalAlignment = CenterHorizontally) {
                Spacer(modifier = Modifier.padding(top = 54.dp))
                Text(
                    "Add Child",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF029094),
                    fontFamily = Poppins
                )
                Spacer(modifier = Modifier.padding(bottom = 54.dp))

                Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Column {
                        OutlinedTextField(
                            value = childName,
                            onValueChange = { childName = it },
                            label = { Text("Nama Anak") },
                            modifier = Modifier
                                .fillMaxWidth(),
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
                            value = dateOfBirth,
                            onValueChange = { dateOfBirth = it },
                            label = { Text("Date 0f Birth (year-month-day)") },
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
                        Spacer(modifier = Modifier.padding(bottom = 20.dp))

                        OutlinedTextField(
                            value = weight,
                            onValueChange = { weight = it },
                            label = { Text("Weight") },
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
                        Spacer(modifier = Modifier.padding(bottom = 20.dp))

                        OutlinedTextField(
                            value = height,
                            onValueChange = { height = it },
                            label = { Text("Height") },
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
                        Spacer(modifier = Modifier.padding(bottom = 20.dp))
                        OutlinedTextField(
                            value = activity,
                            onValueChange = { activity = it },
                            label = { Text("Activity") },
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
                        Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    }
                }
            }
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text("Gender of Child")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedGender == "Boy",
                        onClick = { selectedGender = "Boy" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colors.primary
                        )
                    )
                    Text(
                        text = "Boy",
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    RadioButton(
                        selected = selectedGender == "Girl",
                        onClick = { selectedGender = "Girl" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colors.primary
                        )
                    )
                    Text(
                        text = "Girl",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Button(
                onClick = {
                    createChild(
                        childName,
                        dateOfBirth,
                        selectedGender,
                        activity.toInt(),
                        weight.toInt(),
                        height.toInt(),
                        "Bearer $savedToken",
                        onSuccess = {
                            navController.navigate("profile_screen") {
                                popUpTo("profile_screen")
                            }
                            Toast.makeText(context, "Tambah Anak Berhasil", Toast.LENGTH_SHORT).show()
                        },
                        onFailure = {
                            Toast.makeText(context, "Tambah Anak Gagal", Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 24.dp)
                    .align(CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)),
            ) {
                Text(
                    text = "Save Changes",
                    color = Color.White
                )
            }
        }
    }
}


fun createChild(
    childName: String,
    dateOfBirth: String,
    gender: String,
    activity: Int,
    weight: Int,
    height: Int,
    token: String,
    onSuccess: () -> Unit,
    onFailure: () -> Unit
) {
    val createChildRequest =
        CreateChildRequest(childName, dateOfBirth, gender, activity, weight, height)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ApiClient.apiService.createChild(token, createChildRequest)
            if (response.isSuccessful) {
                val updateProfileResponse = response.body()
                Log.d("Create Child", ": Berhasil : $updateProfileResponse")
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Log.d("Create Child", ": Gagal : ${response.errorBody()?.string()}")
                    onFailure()
                }
            }

        } catch (e: Exception) {
            // Tangani kesalahan, misalnya menampilkan pesan kesalahan kepada pengguna.
            withContext(Dispatchers.Main) {
                // Contoh: Menampilkan pesan kesalahan menggunakan Toast
                Log.d("Create Child", ": ${e.message}")
            }
        }
    }
}