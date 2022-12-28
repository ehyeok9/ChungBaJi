package com.example.temp.todo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.temp.R
import kotlinx.android.synthetic.main.item_check_box.view.*

class TodoAdapter(private val data: ArrayList<checkboxData>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    interface OnCovidClickListener {
        fun onCovidClick(title:String)
    }

    private var covidListener: OnCovidClickListener? = null

    fun setOnCountryClickListener(covidListener: OnCovidClickListener) {
        this.covidListener = covidListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_check_box,parent,false)
        return ViewHolder(view).also { holder ->

            view.tv_title.setOnClickListener {
                this.covidListener?.onCovidClick(data[holder.adapterPosition].title)

            }

        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val checkbox : CheckBox = itemView.findViewById(R.id.checkBox)
        private val tvTitle:TextView=itemView.findViewById(R.id.tv_title)
        fun bind(item: checkboxData) {
            tvTitle.text = item.title
            tvTitle.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        }
    }


}
data class checkboxData(
    val title : String,
    val check : Boolean
)

