package com.growell.model

data class ProfileResponse(
    val payload: Result
)

data class Result(
    val result: Profile
)

data class Profile(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val children: List<Children>
)

data class Children(
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
    val user_id: String
)

data class UpdateProfileRequest(
    val name: String,
    val phone: String,
)

data class UpdateProfileResponse(
    val payload: UpdateProfilePayload
)

data class UpdateProfilePayload(
    val updated: ProfileUpdated
)

data class ProfileUpdated(
    val name: String,
    val email: String,
    val phone: String,
)