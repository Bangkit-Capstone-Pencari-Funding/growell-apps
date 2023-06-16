package com.growell.model

data class GetDiaryPayload(
    val payload: GetDiaryResult
)

data class GetDiaryResult(
    val result: List<GetDiary>
)


data class GetDiary(
    val id: String,
    val date: String,
    val user_id: String,
    val child_id: String,
    val created_at: String,
    val updated_at: String,
    val recipe: List<GetRecipe>
)

data class GetRecipe(
    val id: String,
    val name: String,
    val target_age: Int,
    val estimated_time: Int,
    val rating: Int,
    val picture: String,
    val how_to: String,
    val tools: List<String>?,
    val description: String,
    val created_at: String,
    val updated_at: String,
    val ingredients: List<Ingredient>
)