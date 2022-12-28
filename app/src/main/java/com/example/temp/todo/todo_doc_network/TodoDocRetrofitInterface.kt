package com.example.temp.todo.todo_doc_network

import com.example.temp.todo.models.passport.response.CountryPassPortResponse
import com.example.temp.todo.models.response.ContryEntryInfoResponse
import com.example.temp.tour_add.country_choice.network.CountryChoiceService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoDocRetrofitInterface {

    @GET("/1262000/CountryOverseasArrivalsService/getCountryOverseasArrivalsList")
    fun getContryEntryInfo(@Query("serviceKey") serviceKey:String,
                           @Query("pageNo") pageNo:Int,
                           @Query("numOfRows") numOfRows:Int,
                           @Query("returnType") returnType:String,
                           @Query("cond[country_nm::EQ]") cond:String): Call<ContryEntryInfoResponse>

    @GET("/1262000/EntranceVisaService2/getEntranceVisaList2")
    fun getCountryPassportInfo(@Query("serviceKey") serviceKey:String,
                               @Query("pageNo")  pageNo:Int,
                               @Query("numOfRows") numOfRows:Int,
                               @Query("cond[country_nm::EQ]") cond:String):Call<CountryPassPortResponse>

}