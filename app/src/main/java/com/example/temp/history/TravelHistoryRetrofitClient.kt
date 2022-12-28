package com.example.temp.history

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TravelHistoryRetrofitClient {
    val sRetrofit = initRetrofit()
    private const val BaseURL = "http://plantity.shop/cokothon/"

    private fun initRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}