package com.example.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.temp.databinding.ActivityRecomListBinding

class RecomListActivity : AppCompatActivity() {

    lateinit var recomListAdapter: RecomListAdapter
    val binding by lazy { ActivityRecomListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recom_list)
        initRecycler()

    }

    private fun initRecycler(){
        val list = ArrayList<checkboxData>()
        list.add(checkboxData(title="에펠탑",check = false))
        list.add(checkboxData(title="에펠탑",check = false))
        list.add(checkboxData(title="에펠탑",check = false))
        list.add(checkboxData(title="에펠탑",check = false))
        list.add(checkboxData(title="에펠탑",check = false))

        recomListAdapter= RecomListAdapter(list)
        binding.rvRecomList.adapter=recomListAdapter
        recomListAdapter.notifyDataSetChanged()
    }
}