package com.example.temp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temp.databinding.FragmentHomeBinding


class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.viewFlipper.flipInterval = 3000  //화면 넘김 간격 메서드 (millisceond)
        binding.viewFlipper.startFlipping()

        val myTourListArray = arrayListOf<MyTourListModel>(
//            MyTourListModel("프랑스","2023.04.21~2023.05.14"),
//            MyTourListModel("일본","2023.01.21~2023.01.30"),
//            MyTourListModel("베트남","2023.03.11~2023.03.22"),
//            MyTourListModel("미국","2023.01.11~2023.01.14"),

        )


        if(myTourListArray.isNullOrEmpty()){
            //비어있다면
            binding.notMyHomeList.visibility = View.VISIBLE
            binding.myHomeList.visibility = View.GONE

        }else{
            binding.notMyHomeList.visibility = View.GONE
            binding.myHomeList.visibility = View.VISIBLE
        }

        binding.myHomeList.layoutManager = LinearLayoutManager(requireContext())
        binding.myHomeList.adapter = MyTourListAdapter(requireContext(),myTourListArray)

        return binding.root
    }



}