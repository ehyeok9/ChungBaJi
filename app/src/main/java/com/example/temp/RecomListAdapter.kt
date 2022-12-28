package com.example.temp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class RecomListAdapter (private val data: ArrayList<checkboxData>) : RecyclerView.Adapter<RecomListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recom_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val country : CheckBox = itemView.findViewById(R.id.country)
        fun bind(item: checkboxData) {
            country.text = item.title

        }
    }


}
data class checkboxData(
    val title : String,
    val check : Boolean
)

