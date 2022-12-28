package com.example.temp.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.temp.R
import com.example.temp.databinding.FragmentHistoryBinding
import com.example.temp.history.models.TravelHistoryResponse
import com.example.temp.history.recycler.HistoryAdapter
import com.example.temp.history.recycler.HistoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

   private lateinit var binding: FragmentHistoryBinding
   lateinit var historyAdapter: HistoryAdapter
   val historyDatas = mutableListOf<HistoryData>()

    var testImg: Int = R.drawable.tempimage

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

                    historyAdapter = HistoryAdapter(requireActivity())
                    binding.recyclerHistory.adapter = historyAdapter

                    for(i in result.result.indices){
                        historyDatas.apply { add(HistoryData(img = resources.getDrawable(R.drawable.tempimage),
                            name = result.result[i].country,
                            startDate = result.result[i].startDate,
                            endDate = result.result[i].endDate)) }
                    }

                    historyAdapter.historyDatas = historyDatas
                    historyAdapter.notifyDataSetChanged()


                }else{
                    Log.d("TravelHistory", "onResponse: Error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<TravelHistoryResponse>, t: Throwable) {
                Log.d("TravelHistory", t.message ?: "통신오류")
            }

        })
    }

}