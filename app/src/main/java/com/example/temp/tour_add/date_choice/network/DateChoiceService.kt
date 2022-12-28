package com.example.temp.tour_add.date_choice.network

import android.util.Log
import com.example.temp.tour_add.date_choice.models.request.TourAddRequest
import com.example.temp.tour_add.date_choice.models.response.TourAddResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.X509TrustManager

class DateChoiceService (val dateChoiceActivityInterface:DateChoiceActivityInterface){
    lateinit var retrofit: Retrofit

    fun tryPostTourAdd(params:TourAddRequest){

        val client = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .hostnameVerifier{ hostname, session -> true }
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("http://plantity.shop")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()




        val dateChoiceRetrofitService=retrofit.create(DateChoiceRetrofitInterface::class.java)

        dateChoiceRetrofitService.postTourAdd(params).enqueue(object:Callback<TourAddResponse>{
            override fun onResponse(
                call: Call<TourAddResponse>,
                response: Response<TourAddResponse>
            ) {
                response.body()?.let {
                    dateChoiceActivityInterface.onPostTourAddSuccess(it)
                }
            }

            override fun onFailure(call: Call<TourAddResponse>, t: Throwable) {
                dateChoiceActivityInterface.onPostTourAddFailure(500,"network error")
            }

        })
    }

}