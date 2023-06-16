package com.growell.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.ui.theme.Poppins

@Composable
fun ChildCard(
    childname: String,
    dateOfBirth: String,
    height: String,
    weight: String,
    activity: String,
    gender: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .height(160.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.child_image),
                contentDescription = "child_image"
            )
            Spacer(modifier = Modifier.padding(end = 24.dp))
            Column {
                Text(
                    "Childname",
                    fontSize = 12.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    childname,
                    fontSize = 10.sp,
                    fontFamily = Poppins,
                    color = Color(0xFF666666)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            "Date of birth",
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            dateOfBirth,
                            fontSize = 10.sp,
                            fontFamily = Poppins,
                            color = Color(0xFF666666)
                        )
                    }
                    Column {
                        Text(
                            "Gender",
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            gender,
                            fontSize = 10.sp,
                            fontFamily = Poppins,
                            color = Color(0xFF666666)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column {
                        Text(
                            "Weight",
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            weight,
                            fontSize = 10.sp,
                            fontFamily = Poppins,
                            color = Color(0xFF666666)
                        )
                    }
                    Column {
                        Text(
                            "Height",
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            height,
                            fontSize = 10.sp,
                            fontFamily = Poppins,
                            color = Color(0xFF666666)
                        )
                    }
                    Column {
                        Text(
                            "Activity",
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(activity, fontSize = 10.sp, fontFamily = Poppins, color = Color(0xFF666666))
                    }
                }
            }
        }
    }
}