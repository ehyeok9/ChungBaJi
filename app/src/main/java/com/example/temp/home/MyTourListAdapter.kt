package com.example.temp.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.temp.databinding.ItemTourCardBinding
import com.example.temp.todo.TodoActivity


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

        holder.binding.root.setOnClickListener{
            Log.i("click","${holder.binding.country.text.toString()}")
            val intent = Intent(holder.binding.root?.context, TodoActivity::class.java)
            intent.putExtra("clickMyList","${holder.binding.country.text.toString()} ")
            ContextCompat.startActivity(holder.binding.root.context, intent,null)
        }


    }
    class ViewHolder(val context: FragmentActivity?, val binding:ItemTourCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MyTourListModel){
            binding.country.text = item.country
            binding.date.text = item.sDate + "~" + item.eDate
        }
    }
}