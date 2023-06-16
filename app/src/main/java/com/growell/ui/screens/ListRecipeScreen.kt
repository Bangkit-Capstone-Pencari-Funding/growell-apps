package com.growell.ui.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.growell.ui.theme.GrowellTheme
import com.growell.R
import com.growell.api.ApiClient
import com.growell.model.Recipe
import com.growell.ui.components.RecipeListItemSmall

@Composable
fun ListRecipeScreen(ingredient: String?, navController: NavController) {
    var recipes by remember {
        mutableStateOf(emptyList<Recipe>())
    }
    LaunchedEffect(Unit) {
        try {
            val response = ingredient?.let { ApiClient.apiService.getRecipesByIngridients(it) }
            if (response != null) {
                if (response.isSuccessful) {
                    val recipeList = response.body()?.payload?.result
                    Log.d("List Recipe", "list Recipe: $recipeList")
                    if (!recipeList.isNullOrEmpty()) {
                        recipes = recipeList
                    }
                } else {
                    // Handle error response
                }
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }
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
                items(recipes) {recipe ->
                    RecipeListItemSmall(
                        onClick = {
                            navController.navigate("detail_recipe_screen/${recipe.id}")
                        },
                        name = recipe.name,
                        rating = recipe.rating.toString(),
                        image = recipe.picture,
                        estimated_time = recipe.estimated_time.toString(),
                    )
                }
            }
        )
    }
}