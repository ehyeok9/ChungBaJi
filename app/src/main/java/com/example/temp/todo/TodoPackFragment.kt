package com.example.temp.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temp.R
import com.example.temp.databinding.FragmentTodoPackBinding

class TodoPackFragment : Fragment() {
    lateinit var todoAdapter: TodoAdapter
    private lateinit var binding : FragmentTodoPackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoPackBinding.inflate(layoutInflater)
        initRecycler()

        return binding.root
    }
    private fun initRecycler(){
        val list = ArrayList<checkboxData>()
        list.add(checkboxData(title="비상약", check = false))
        list.add(checkboxData(title="보조배터리", check = false))
        list.add(checkboxData(title="충전기", check = false))
        list.add(checkboxData(title="우산", check = false))
        list.add(checkboxData(title="속옷", check = false))
        list.add(checkboxData(title="칫솔", check = false))
        list.add(checkboxData(title="치약", check = false))
        list.add(checkboxData(title="폼클렌저", check = false))
        list.add(checkboxData(title="샴푸", check = false))
        list.add(checkboxData(title="바디워시", check = false))
        list.add(checkboxData(title="카메라", check = false))
        list.add(checkboxData(title="수영복", check = false))
        list.add(checkboxData(title="선크림", check = false))

        todoAdapter= TodoAdapter(list)
        binding.rv.adapter=todoAdapter
        todoAdapter.notifyDataSetChanged()


    }
}