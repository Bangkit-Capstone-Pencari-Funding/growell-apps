package com.growell.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient2 {

    private const val BASE_URL2 = "https://growell-ml-predict-6sngjta7xq-et.a.run.app/"

    private val retrofit2: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    val apiService2: ApiService2 by lazy {
        retrofit2.create(ApiService2::class.java)
    }
}