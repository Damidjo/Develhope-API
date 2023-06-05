package com.android.example.develhope_basketball

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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

            val request = chain.request()
            val url: HttpUrl = request.url.newBuilder()
                .addQueryParameter("key", "6b471ad4224e4830a246280a00c5bc94")
                .build()

          val newRequest = request.newBuilder()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "rawg-video-games-database.p.rapidapi.com")
                .url(url)
                .build()
            return chain.proceed(newRequest)
        }
    }
}



