package com.example.temp.history.img

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HistoryImgRetrofitClient {
    val sRetrofit = initRetrofit()
    private const val BaseURL = "https://dapi.kakao.com/v2/search/"

    private fun initRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}