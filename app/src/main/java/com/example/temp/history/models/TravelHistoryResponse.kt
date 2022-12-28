package com.example.temp.history.models

data class TravelHistoryResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)