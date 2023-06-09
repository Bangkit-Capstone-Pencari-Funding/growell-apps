package com.growell.ui.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.growell.R
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeListItemSmall(
    name: String?,
    rating: String?,
    estimated_time: String?,
    image: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .clickable{ onClick()}
    ) {
        Column(
            modifier = Modifier.padding(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            ) {
                GlideImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color.White)
                        .align(Alignment.TopStart)
                        .padding(horizontal = 16.dp, vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (rating != null) {
                            Text(
                                text = rating,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Icon(
                            painter = painterResource(R.drawable.star_icon),
                            contentDescription = "icon_star",
                            tint = Color(0xFFFED235)
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 13.dp)
            ) {
                if (name != null) {
                    Text(
                        text = name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Poppins
                    )
                }
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
                    text = "$estimated_time mins",
                    fontSize = 9.sp,
                    fontFamily = Poppins
                )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = Color(0xFFD9FFEA), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            "Vegetable",
                            color = Color(0xFF31817C),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Poppins
                        )
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color(0xFFD9FFEA), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            "Bread",
                            color = Color(0xFF31817C),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Poppins
                        )
                    }
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(43.dp)
                        .height(13.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF029094)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Cook",
                        style = MaterialTheme.typography.button.copy(
                            fontSize = 9.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}