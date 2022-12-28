package com.example.temp.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.temp.databinding.ItemTourCardBinding

class MyTourListAdapter(val context: Context, private val myTourListArray: ArrayList<MyTourListModel>): RecyclerView.Adapter<MyTourListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemTourCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return  myTourListArray.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = myTourListArray[position]

        holder.binding.country.text = currentItem.country
        holder.binding.date.text = currentItem.sDate+"~"+currentItem.eDate
    }
    class ListViewHolder(val binding: ItemTourCardBinding) : RecyclerView.ViewHolder(binding.root)
}