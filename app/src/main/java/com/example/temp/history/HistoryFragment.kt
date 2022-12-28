package com.example.temp.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.temp.R
import com.example.temp.databinding.FragmentHistoryBinding
import com.example.temp.history.recycler.HistoryAdapter
import com.example.temp.history.recycler.HistoryData

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

        recyclerViewHistory()
        return binding.root
    }


    fun recyclerViewHistory(){
        historyAdapter = HistoryAdapter(requireActivity())
        binding.recyclerHistory.adapter = historyAdapter

        for(i in 1 until 5){
            historyDatas.apply { add(HistoryData(img = resources.getDrawable(R.drawable.tempimage), name = "프랑스", date = "20202020202")) }
        }

        historyAdapter.historyDatas = historyDatas
        historyAdapter.notifyDataSetChanged()
    }

}