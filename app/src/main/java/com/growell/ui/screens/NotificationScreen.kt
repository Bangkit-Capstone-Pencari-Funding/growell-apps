package com.growell.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .padding(bottom = 24.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 36.dp, horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.arrow_big_green_left),
                contentDescription = "back_green_icon",
            )
            Spacer(modifier = Modifier.padding(end = 24.dp))
            Text(
                "Notification",
                fontSize = 26.sp,
                color = Color(0xFF43ADA6),
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.reminder_logo),
                contentDescription = "reminder_notif",
                modifier = Modifier
                    .width(46.dp)
                    .height(46.dp)
            )
            Spacer(modifier = Modifier.padding(end = 12.dp))
            Column {
                Text("Reminder", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text(
                    "Your password has been restored\nsuccessfully",
                    fontSize = 14.sp,
                    color = Color(0xFF7F7F7F)
                )
            }
            Spacer(modifier = Modifier.padding(end = 12.dp))
            Text("4 days", fontSize = 14.sp, color = Color(0xFF7F7F7F))
        }
        Spacer(modifier = Modifier.padding(bottom = 40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.reminder_logo),
                contentDescription = "reminder_notif",
                modifier = Modifier
                    .width(46.dp)
                    .height(46.dp)
            )
            Spacer(modifier = Modifier.padding(end = 12.dp))
            Column {
                Text("Reminder", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text(
                    "Your password has been restored\nsuccessfully",
                    fontSize = 14.sp,
                    color = Color(0xFF7F7F7F)
                )
            }
            Spacer(modifier = Modifier.padding(end = 12.dp))
            Text("4 days", fontSize = 14.sp, color = Color(0xFF7F7F7F))
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun NotificationScreenPreview() {
    GrowellTheme(
        darkTheme = false,
    ) {
        NotificationScreen()
    }
}