package com.growell.api

import com.growell.model.DetailRecipeResponse
import com.growell.model.GetDiaryPayload
import com.growell.model.ProfileResponse
import com.growell.model.RecipeResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://capstone-project-growell.et.r.appspot.com/v1/"


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    suspend fun getRecipes(token: String?): Response<RecipeResponse> {
        val authorizationHeader = token?.let { "Bearer $it" } ?: ""
        return apiService.getRecipes(authorizationHeader)
    }

    suspend fun getProfile(token: String?): Response<ProfileResponse> {
        val authorizationHeader = token?.let { "Bearer $it" } ?: ""
        return apiService.getProfile(authorizationHeader)
    }

    suspend fun getDiaryData(childId: String, date: String, token: String?): Response<GetDiaryPayload> {
        val authorizationHeader = token?.let { "Bearer $it" } ?: ""
        return apiService.getDiaryData(childId, date, authorizationHeader)
    }
}
