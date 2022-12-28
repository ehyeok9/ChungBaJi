package com.example.temp.todo.todo_doc_network

import android.util.Log
import com.example.temp.todo.models.passport.response.CountryPassPortResponse
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
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class TodoDocService(val todoDockFragmentInterface: TodoDocFragmentInterface) {
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




        val todoRetrofitService=retrofit.create(TodoDocRetrofitInterface::class.java)

        todoRetrofitService.getContryEntryInfo(serviceKey,pageNo,numOfRows,returnType,cond).enqueue(object:Callback<ContryEntryInfoResponse>{
            override fun onResponse(
                call: Call<ContryEntryInfoResponse>,
                response: Response<ContryEntryInfoResponse>
            ) {
                response.body()?.let {
                    todoDockFragmentInterface.onContryEntryInfoSuccess(it)
                }
                Log.d("todoservice",response.toString())
            }

            override fun onFailure(call: Call<ContryEntryInfoResponse>, t: Throwable) {
                todoDockFragmentInterface.onContryEntryInfoFailure("network error",500)
                Log.d("todoservice","network error")
            }

        })

    }



    fun tryGetCountryPassPort(serviceKey:String,pageNo:Int,numOfRows:Int,cond:String){

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




        val todoRetrofitService=retrofit.create(TodoDocRetrofitInterface::class.java)

        todoRetrofitService.getCountryPassportInfo(serviceKey,pageNo,numOfRows,cond).enqueue(object :Callback<CountryPassPortResponse>{
            override fun onResponse(
                call: Call<CountryPassPortResponse>,
                response: Response<CountryPassPortResponse>
            ) {
                response.body()?.let {
                    todoDockFragmentInterface.onGetCountryPassPortSuccess(it)
                }

            }

            override fun onFailure(call: Call<CountryPassPortResponse>, t: Throwable) {
                todoDockFragmentInterface.onGetCountryPassPortFailure("network error" , 505)
            }

        })

    }

}