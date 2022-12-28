package com.example.temp.todo.network

import com.example.temp.todo.models.response.ContryEntryInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoRetrofitInterface {

    @GET("/1262000/CountryOverseasArrivalsService/getCountryOverseasArrivalsList")
    fun getContryEntryInfo(@Query("serviceKey") serviceKey:String,
                           @Query("pageNo") pageNo:Int,
                           @Query("numOfRows") numOfRows:Int,
                           @Query("returnType") returnType:String,
                           @Query("cond[country_nm::EQ]") cond:String): Call<ContryEntryInfoResponse>

}