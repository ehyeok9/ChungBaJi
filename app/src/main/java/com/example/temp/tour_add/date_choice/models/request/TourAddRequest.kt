package com.example.temp.tour_add.date_choice.models.request

import com.google.gson.annotations.SerializedName

data class TourAddRequest(
    @SerializedName("country") val country:String,
    @SerializedName("startDate") val startDate:String,
    @SerializedName("endDate") val endDate:String
)