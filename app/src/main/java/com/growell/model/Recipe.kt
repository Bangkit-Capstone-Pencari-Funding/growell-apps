package com.growell.model

data class Recipe(
    val id: String,
    val name: String,
    val targetAge: Int,
    val estimatedTime: Int,
    val rating: Int,
    val picture: String,
    val howTo: String,
    val tools: List<String>?,
    val description: String,
    val createdAt: String,
    val updatedAt: String
)

data class RecipeResponse(
    val payload: PayloadRecipe
)

data class PayloadRecipe(
    val recipe: List<Recipe>
)