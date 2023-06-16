package com.growell.model

data class DiaryRequest(
    val content: String,
    val picture: String,
    val rating: Int,
    val child_id: String,
)

data class DiaryResponse(
    val payload: DiaryPayload
)

data class DiaryPayload(
    val created: Diary
)

data class Diary(
    val id: String,
    val user_id: String,
    val recipe_id: String,
    val content: String,
    val picture: String,
    val rating: Int,
    val created_at: String,
    val updated_at: String,
)
