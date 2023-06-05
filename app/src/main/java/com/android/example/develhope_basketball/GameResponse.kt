package com.android.example.develhope_basketball

data class GameResponse(
    val name: String,
)

data class ResultResponse(val results: List<GameResponse>)
