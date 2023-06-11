package com.growell.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
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
fun EditProfileScreen() {
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
                value = "",
                onValueChange = {},
                label = { Text("Tuliskan Nama Lengkap") },
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
            Spacer(modifier = Modifier.padding(bottom = 16.dp))

            Text(
                "Email",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Tuliskan Email Anda") },
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
            Spacer(modifier = Modifier.padding(bottom = 16.dp))

            Text(
                "Umur",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Tuliskan Umur Anda") },
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
            Spacer(modifier = Modifier.padding(bottom = 16.dp))

            Text(
                "Alamat",
                fontSize = 14.sp,
                fontFamily = Poppins,
                color = Color(0xFF695C5C),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Tuliskan Alamat Lengkap") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
//            keyboardOptions = KeyboardOptions(email = true),
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 64.dp))
        }
        Button(
            onClick = {},
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

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun EditProfileScreenPreview() {
    GrowellTheme(darkTheme = false) {
        EditProfileScreen()
    }
}