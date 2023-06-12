package com.growell.api

import com.growell.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

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
}