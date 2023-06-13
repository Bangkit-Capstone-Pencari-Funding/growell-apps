package com.growell.model

data class CreateChildRequest(
    val childName: String,
    val dateOfBirth: String,
    val gender: String,
    val activity: Int,
    val weight: Int,
    val height: Int,
)

data class CreateChildResponse(
    val payload: Created
)

data class Created(
    val created: Child
)

data class Child(
    val id: String,
    val name: String,
    val date_of_birth: String,
    val height: Int,
    val weight: Int,
    val activity: Int,
    val created_at: String,
    val updated_at: String,
    val calories_needed: Int,
    val gender: String,
    val user_id: String,
)