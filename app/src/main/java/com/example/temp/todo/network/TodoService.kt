package com.example.temp.todo.network

import android.util.Log
import com.example.temp.todo.models.response.ContryEntryInfoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.X509Certificate

class TodoService(val todoActivityInterface: TodoActivityInterface) {
    lateinit var retrofit: Retrofit

    fun tryGetContryEntryInfo(serviceKey:String,pageNo:Int,numOfRows:Int,returnType:String,cond:String){

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

            override fun checkClientTrusted(
                p0: Array<out java.security.cert.X509Certificate>?,
                p1: String?
            ) {
            }

            override fun checkServerTrusted(
                p0: Array<out java.security.cert.X509Certificate>?,
                p1: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? {
                return arrayOf()
            }
        })



        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        val client = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier{ hostname, session -> true }
            .build()





//        client.setSocket(sslContext.getSocketFactory().createSocket())

        retrofit = Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()




        val todoRetrofitService=retrofit.create(TodoRetrofitInterface::class.java)

        todoRetrofitService.getContryEntryInfo(serviceKey,pageNo,numOfRows,returnType,cond).enqueue(object:Callback<ContryEntryInfoResponse>{
            override fun onResponse(
                call: Call<ContryEntryInfoResponse>,
                response: Response<ContryEntryInfoResponse>
            ) {
                response.body()?.let {
                    todoActivityInterface.onContryEntryInfoSuccess(it)
                }
                Log.d("todoservice",response.toString())
            }

            override fun onFailure(call: Call<ContryEntryInfoResponse>, t: Throwable) {
                todoActivityInterface.onContryEntryInfoFailure("network error",500)
                Log.d("todoservice","network error")
            }

        })

    }

}