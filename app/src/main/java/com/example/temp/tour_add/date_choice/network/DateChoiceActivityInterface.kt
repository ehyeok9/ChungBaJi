package com.example.temp.tour_add.date_choice.network

import com.example.temp.tour_add.date_choice.models.request.TourAddRequest
import com.example.temp.tour_add.date_choice.models.response.TourAddResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DateChoiceActivityInterface {
    fun onPostTourAddSuccess(response:TourAddResponse)
    fun onPostTourAddFailure(code:Int,message:String)
}