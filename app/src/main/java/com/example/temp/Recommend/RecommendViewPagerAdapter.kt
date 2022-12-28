package com.example.temp.Recommend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.temp.R

class RecommendViewPagerAdapter(picList: ArrayList<ViewPagerData>) :
    RecyclerView.Adapter<RecommendViewPagerAdapter.PagerViewHolder>() {
    val item = picList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.pic.setImageResource(item[position].img)
        holder.country.setText(item[position].country)
        holder.detail.setText(item[position].detail)

    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.recommend_viewpager, parent, false)){
        val pic: ImageView = itemView.findViewById<ImageView>(R.id.imageView_pic)
        val country: TextView = itemView.findViewById(R.id.country)
        val detail: TextView = itemView.findViewById(R.id.detail)
    }
}