package com.example.temp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.temp.databinding.ItemRecomCardBinding

class RecomListAdapter (private val data: ArrayList<checkboxData>) : RecyclerView.Adapter<RecomListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecomCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(val binding: ItemRecomCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: checkboxData) {
            binding.spot.text = item.title
            binding.comment.text = item.comment

            // 이미지 url 읽어서 넣기
            Glide.with(itemView).load(item.img).into(binding.imageView)
        }
    }


}
data class checkboxData(
    val img : String,
    val title : String,
    val comment : String,
)