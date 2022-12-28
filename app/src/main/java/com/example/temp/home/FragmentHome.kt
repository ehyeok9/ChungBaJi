package com.example.temp.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.temp.R
import com.example.temp.databinding.FragmentHomeBinding
import com.example.temp.history.TravelHistoryInterface
import com.example.temp.history.TravelHistoryRetrofitClient
import com.example.temp.history.models.TravelHistoryResponse
import com.example.temp.history.recycler.HistoryAdapter
import com.example.temp.history.recycler.HistoryData
import kotlinx.android.synthetic.main.item_recom_card.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    lateinit var myTourListAdapter: MyTourListAdapter
    val  myTourListArray = mutableListOf<MyTourListModel>()


//    var myTourListArray: ArrayList<MyTourListModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.viewFlipper.flipInterval = 3000  //화면 넘김 간격 메서드 (millisceond)
        binding.viewFlipper.startFlipping()

//        val myTourListArray = arrayListOf<MyTourListModel>(
////            MyTourListModel("프랑스","2023.04.21~2023.05.14"),
////            MyTourListModel("일본","2023.01.21~2023.01.30"),
////            MyTourListModel("베트남","2023.03.11~2023.03.22"),
////            MyTourListModel("미국","2023.01.11~2023.01.14"),
//
//        )
        getTravelHistoryData()

        if(myTourListArray.isNullOrEmpty()){
            //비어있다면
            binding.notMyHomeList.visibility = View.VISIBLE
            binding.myHomeList.visibility = View.GONE

        }else{
            binding.notMyHomeList.visibility = View.GONE
            binding.myHomeList.visibility = View.VISIBLE
        }

//        binding.myHomeList.layoutManager = LinearLayoutManager(requireContext())
//        binding.myHomeList.adapter = MyTourListAdapter(requireContext(),myTourListArray)
        var today = Calendar.getInstance()
        Log.i("test","오늘의 날짜 : ${today}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        showAllList()
//
//        binding.myHomeList.setHasFixedSize(true)
//
//        var manager = LinearLayoutManager(requireContext())
//        binding.myHomeList.layoutManager = manager
//
//        var chAdapter = MyTourListAdapter(requireContext(),myTourListArray)
//        binding.myHomeList.adapter = chAdapter
    }

//    private fun showAllList() {
//        val url = "http://plantity.shop/cokothon/list"
//        val sr = StringRequest(
//            Request.Method.GET, url,
//            { response: String? ->
//                myTourListArray =
//                    ArrayList<MyTourListModel>()
//
//                try {
//
//                    val jsonArray = JSONArray(response)
//
//                    for (i in 0 until jsonArray.length()) {
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        val country = jsonObject.getString("country")
//                        val start = jsonObject.getString("startDate")
//                        val end = jsonObject.getString("endDate")
//
//                        var today = Calendar.getInstance()
//                        Log.i("test","오늘의 날짜 : ${today}")
//                        Log.i("test","나라 : ${country}")
//
//                        myTourListArray.add(
//                            MyTourListModel(
//                                    country, start,end
//                                )
//                            )
//                    }
//                    var newAdapter = MyTourListAdapter(requireContext(),myTourListArray)
//                    binding.myHomeList.adapter = newAdapter
//
//
//
//                } catch (e: Exception) {
//                    Log.e("SearchListJSON", response!!)
//                }
//                var newAdapter = MyTourListAdapter(requireContext(),myTourListArray)
//                binding.myHomeList.adapter = newAdapter
//
//                Log.i("imageAdd","이미지addlist확인: ${myTourListArray}")
//
//
//
//            }
//        ) { error: VolleyError ->
//            Log.e(
//                "myList",
//                "ee"
//            )
//        }
//        sr.setShouldCache(false)
//        queue = Volley.newRequestQueue(requireContext())
//        queue!!.add(sr)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getTravelHistoryData(){
        val travelHistoryInterface = TravelHistoryRetrofitClient.sRetrofit.create(
            TravelHistoryInterface::class.java)
        travelHistoryInterface.getTravelHistoryList().enqueue(object :
            Callback<TravelHistoryResponse> {
            override fun onResponse(
                call: Call<TravelHistoryResponse>,
                response: Response<TravelHistoryResponse>
            ) {
                if(response.isSuccessful){
                    val result = response.body() as TravelHistoryResponse

                    myTourListAdapter = MyTourListAdapter(requireActivity())
                    binding.myHomeList.adapter = myTourListAdapter

                    for(i in result.result.indices){
                        myTourListArray.apply { add(MyTourListModel(
                            country = result.result[i].country,
                            sDate = result.result[i].startDate,
                            eDate = result.result[i].endDate)
                        ) }
                    }

                    myTourListAdapter.myTourListArray = myTourListArray
                    myTourListAdapter.notifyDataSetChanged()
                    if(myTourListArray.isNullOrEmpty()){
                        //비어있다면
                        binding.notMyHomeList.visibility = View.VISIBLE
                        binding.myHomeList.visibility = View.GONE

                    }else{
                        binding.notMyHomeList.visibility = View.GONE
                        binding.myHomeList.visibility = View.VISIBLE
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



}