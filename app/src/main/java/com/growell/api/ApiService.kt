package com.growell.api

import com.growell.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // LOGIN
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // REGISTER
    @POST("auth/signup")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    // GET RECIPE LIST HOME
    @GET("home")
    suspend fun getRecipes(@Header("Authorization") token: String): Response<RecipeResponse>

    @GET("recipe/{recipeId}")
    suspend fun getRecipeDetail(
        @Path("recipeId") recipeId: String
    ): Response<DetailRecipeResponse>

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

    @PUT("profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body request: UpdateProfileRequest
    ): Response<UpdateProfileResponse>
}