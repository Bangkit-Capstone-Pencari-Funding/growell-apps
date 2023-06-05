package com.growell.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun RecipeListItem(
//    name: String?,
//    rating: String?,
//    image: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.image_food_1),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 13.dp)
            ) {
                Text(
                    text = "Sandwich",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Image(
                    painter = painterResource(R.drawable.icon_checklist),
                    contentDescription = "icon_checklist",
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(2.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 13.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.clock_icon),
                    contentDescription = "clock_icon",
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "10 - 15 mins",
                    fontSize = 12.sp,
                    fontFamily = Poppins
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemListPreview() {
    GrowellTheme {
        RecipeListItem()
    }
}