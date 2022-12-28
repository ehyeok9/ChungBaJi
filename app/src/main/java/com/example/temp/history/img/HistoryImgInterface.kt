package com.example.temp.history.img

import com.example.temp.history.models.img.HistoryImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HistoryImgInterface {
    @GET("image")
    fun getHistoryImg(
        @Query("query") query: String,
        @Header("Authorization") Authorization: String
    ): Call<HistoryImageResponse>
}