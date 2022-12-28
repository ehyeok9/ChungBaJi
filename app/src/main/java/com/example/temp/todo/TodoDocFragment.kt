package com.example.temp.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.temp.R
import com.example.temp.databinding.FragmentTodoDocBinding
import com.example.temp.databinding.FragmentTodoPackBinding

class TodoDocFragment : Fragment() {
    lateinit var todoAdapter: TodoAdapter
    private lateinit var binding : FragmentTodoDocBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoDocBinding.inflate(layoutInflater)
        initRecycler()
        return binding.root
    }

    private fun initRecycler(){
        val list = ArrayList<checkboxData>()
        list.add(checkboxData(title="여권", check = false))
        list.add(checkboxData(title="백신증명서", check = false))
        list.add(checkboxData(title="비자 발급", check = false))
        list.add(checkboxData(title="국제운전면허증", check = false))
        list.add(checkboxData(title="숙소 바우처", check = false))
        list.add(checkboxData(title="dd", check = false))
        list.add(checkboxData(title="dd", check = false))

        todoAdapter= TodoAdapter(list)
        binding.rv.adapter=todoAdapter
        todoAdapter.notifyDataSetChanged()


    }
}