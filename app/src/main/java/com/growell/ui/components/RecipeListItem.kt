package com.growell.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.growell.R
import com.growell.ui.theme.GrowellTheme

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

            Text(
                text = "Title",
                style = MaterialTheme.typography.h6
            )

            Text(
                text = "Subtitle",
                style = MaterialTheme.typography.body2
            )
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