package com.growell.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun SignInScreen() {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
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
                "Welcome Back! \uD83D\uDC4B",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                color = Color(0xFF343434)
            )
        }
        Column(modifier = Modifier.padding(horizontal = 40.dp)) {
            Text(
                "Email",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                label = { Text("example@gmail.com") },
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
            Text(
                "Password",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                label = { Text("enter your password") },
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
            Spacer(modifier = Modifier.padding(bottom = 78.dp))
            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)) // Warna latar belakang #43ADA6
            ) {
                Text(
                    text = "Login",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 78.dp))
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
                    Text(text = "Login with Google")
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 35.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    "Don’t have an account ? ",
                    color = Color(0xFF0D0E0E),
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                Text(
                    "Sign Up",
                    color = Color(0xFF43ADA6),
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun SignInScreenPreview() {
    GrowellTheme {
        SignInScreen()
    }
}