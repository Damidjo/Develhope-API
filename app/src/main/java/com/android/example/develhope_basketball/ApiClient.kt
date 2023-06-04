package com.android.example.develhope_basketball

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://rawg-video-games-database.p.rapidapi.com"

    fun create(apiKey: String): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "rawg-video-games-database.p.rapidapi.com")
                .build()
            return chain.proceed(request)
        }
    }
}



