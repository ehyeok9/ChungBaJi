package com.example.temp.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.temp.databinding.ItemTourCardBinding


class MyTourListAdapter(val context: FragmentActivity?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var myTourListArray= mutableListOf<MyTourListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemTourCardBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(context, listItemBinding)
    }

    override fun getItemCount(): Int {
        return  myTourListArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(myTourListArray[position])
    }
    class ViewHolder(val context: FragmentActivity?, val binding:ItemTourCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MyTourListModel){
            binding.country.text = item.country
            binding.date.text = item.sDate + "~" + item.eDate
        }
    }
}