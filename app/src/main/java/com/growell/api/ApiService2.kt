package com.growell.api

import com.growell.model.ImageModelResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService2 {
    @Multipart
    @POST("predict")
    suspend fun analyzeImage(@Part image: MultipartBody.Part): Response<ImageModelResponse>
}