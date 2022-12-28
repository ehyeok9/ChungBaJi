package com.example.temp.history

import com.example.temp.history.models.TravelHistoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface TravelHistoryInterface {
    @GET("list")
    fun getTravelHistoryList(): Call<TravelHistoryResponse>
}