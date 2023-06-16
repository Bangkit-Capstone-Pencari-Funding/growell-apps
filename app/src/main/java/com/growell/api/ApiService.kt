package com.growell.api

import com.growell.model.*
import okhttp3.MultipartBody
import retrofit2.Call
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

    @POST("profile/child")
    suspend fun createChild(
        @Header("Authorization") token: String,
        @Body request: CreateChildRequest
    ): Response<CreateChildResponse>

    @POST("recipe/{recipeId}/comments")
    suspend fun createDiary(
        @Header("Authorization") token: String,
        @Path("recipeId") recipeId: String,
        @Body request: DiaryRequest
    ): Response<DiaryResponse>

    @GET("diary/check/{childId}")
    suspend fun getDiaryData(
        @Path("childId") childId: String,
        @Query("date") date: String,
        @Header("Authorization") token: String
    ): Response<GetDiaryPayload>

    @GET("recipe")
    suspend fun getRecipesByIngridients(
        @Query("ingredients") ingredients: String,
    ): Response<RecipeByIngredientsResponse>

}