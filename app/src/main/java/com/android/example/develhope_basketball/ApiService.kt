package com.android.example.develhope_basketball

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/games")
    suspend fun fetchData(): Response<ApiResponse>
}