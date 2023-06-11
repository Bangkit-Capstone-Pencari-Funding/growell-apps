package com.growell.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.ui.theme.GrowellTheme
import com.growell.R
import com.growell.ui.components.RecipeListItemSmall

@Composable
fun ListRecipeScreen() {
    val list = (1..10).map { it.toString() }
    Column(

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 24.dp, 24.dp, 18.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(30.dp))
                    .padding(vertical = 11.dp, horizontal = 14.dp)
                    .shadow(
                        elevation = 30.dp,
                        shape = RoundedCornerShape(10.dp),
                        ambientColor = Color.Black.copy(alpha = 0.25f),
                    )
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(R.drawable.arrow_left_icon),
                    contentDescription = "Icon_Back",
                )
            }
            Spacer(modifier = Modifier.padding(start = 24.dp))
            Text(
                "List Recipe",
                color = Color(0xFF43ADA6),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(5) {
                    RecipeListItemSmall()
                }
            }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun ListRecipeScreenPreview() {
    GrowellTheme(darkTheme = false) {
        ListRecipeScreen()
    }
}