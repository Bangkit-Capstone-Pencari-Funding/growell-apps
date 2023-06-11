package com.growell.ui.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun AddChildScreen() {
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
                            value = "",
                            onValueChange = {},
                            label = { Text("Nama Anak") },
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
                        Spacer(modifier = Modifier.padding(bottom = 20.dp))

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("Age") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 56.dp),
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
                            value = "",
                            onValueChange = {},
                            label = { Text("Weight") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 56.dp),
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
                onClick = {},
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp).align(CenterHorizontally),
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


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun AddChildScreenPreview() {
    GrowellTheme(darkTheme = false) {
        AddChildScreen()
    }
}