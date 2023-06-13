package com.growell.model

data class DetailRecipeResponse(
    val payload: RecipeDetail
)

data class RecipeDetail(
    val result: RecipeById,
    val comment: List<Comment>,
    val ingredients: List<Ingredient>
)

data class RecipeById(
    val id: String,
    val name: String,
    val target_age: Int,
    val estimated_time: Int,
    val rating: Int,
    val picture: String,
    val how_to: String,
    val tools: List<String>?,
    val description: String,
    val createdAt: String,
    val updatedAt: String
)

data class Comment(
    val id: String,
    val user_id: String,
    val recipe_id: String,
    val content: String,
    val picture: String,
    val rating: Int,
    val created_at: String,
    val updated_at: String
)

data class Ingredient(
    val id: String,
    val name: String,
    val calories: Int,
    val picture: String?
)