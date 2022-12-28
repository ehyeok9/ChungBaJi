package com.example.temp.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.temp.R
import com.example.temp.databinding.FragmentHistoryBinding
import com.example.temp.history.img.HistoryImgInterface
import com.example.temp.history.img.HistoryImgRetrofitClient
import com.example.temp.history.models.TravelHistoryResponse
import com.example.temp.history.models.img.HistoryImageResponse
import com.example.temp.history.recycler.HistoryAdapter
import com.example.temp.history.recycler.HistoryData
import kotlinx.android.synthetic.main.activity_todo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime

class HistoryFragment : Fragment() {

   private lateinit var binding: FragmentHistoryBinding
   lateinit var historyAdapter: HistoryAdapter
   val historyDatas = mutableListOf<HistoryData>()
    var imgUrl: String ?= null
    var name: String ?= null
    var startDate: String ?= null
    var endDate: String ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        getTravelHistoryData()
        return binding.root
    }


    private fun getTravelHistoryData(){
        val travelHistoryInterface = TravelHistoryRetrofitClient.sRetrofit.create(TravelHistoryInterface::class.java)
        travelHistoryInterface.getTravelHistoryList().enqueue(object : Callback<TravelHistoryResponse>{
            override fun onResponse(
                call: Call<TravelHistoryResponse>,
                response: Response<TravelHistoryResponse>
            ) {
                if(response.isSuccessful){
                    val result = response.body() as TravelHistoryResponse

                    for(i in result.result.indices){
                        val dateAndtime: LocalDateTime = LocalDateTime.now()

                        var dateAndTimeInt = dateAndtime.toString().substring(0 until 4).toInt() // 오늘 년도
                        var startDateInt = result.result[i].startDate.substring(0 until 4).toInt()


                        if(!(dateAndTimeInt < startDateInt)){
                            name = result.result[i].country
                            startDate = result.result[i].startDate
                            endDate = result.result[i].endDate
                            getHistoryImgData(result.result[i].country, "KakaoAK 8909308764caae2bfc804ee924446495")
                        }

                    }

                }else{
                    Log.d("TravelHistory", "onResponse: Error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<TravelHistoryResponse>, t: Throwable) {
                Log.d("TravelHistory", t.message ?: "통신오류")
            }

        })
    }

    private fun getHistoryImgData(query: String,Authorization: String){
        val imgInterface = HistoryImgRetrofitClient.sRetrofit.create(HistoryImgInterface::class.java)
        imgInterface.getHistoryImg(query,Authorization).enqueue(object : Callback<HistoryImageResponse>{
            override fun onResponse(
                call: Call<HistoryImageResponse>,
                response: Response<HistoryImageResponse>
            ) {
                if(response.isSuccessful){
                    val result = response.body() as HistoryImageResponse
                    imgUrl = result.documents[0].image_url

                    historyAdapter = HistoryAdapter(requireActivity())
                    binding.recyclerHistory.adapter = historyAdapter

                    historyDatas.apply { add(HistoryData(img = imgUrl!!,
                        name = name!!,
                        startDate = startDate!!,
                        endDate = endDate!!)) }

                    historyAdapter.historyDatas = historyDatas
                    historyAdapter.notifyDataSetChanged()

                }else{
                    Log.d("HistoryImg", "onResponse: Error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<HistoryImageResponse>, t: Throwable) {
                Log.d("HistoryImg", t.message ?: "통신오류")
            }

        })
    }

}