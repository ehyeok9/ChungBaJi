package com.example.temp.history

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TravelHistoryRetrofitClient {
    val sRetrofit = initRetrofit()
    private const val WEATHER_URL = "http://plantity.shop/cokothon/"

    private fun initRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}