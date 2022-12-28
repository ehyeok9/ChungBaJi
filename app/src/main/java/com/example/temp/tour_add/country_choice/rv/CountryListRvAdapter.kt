package com.example.temp.tour_add.country_choice.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.temp.databinding.ItemCountryListBinding

class CountryListRvAdapter(val dataSet:ArrayList<CountryListDataClass>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnCountryClickListener {
        fun onItemClick(country:String?)
    }

    private var countryListener: OnCountryClickListener? = null

    fun setOnCountryClickListener(countryListener: OnCountryClickListener) {
        this.countryListener = countryListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding=ItemCountryListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return countryViewHolder(binding).also { holder->
            binding.itemCountryListTvName.setOnClickListener {
                this.countryListener?.onItemClick(dataSet[holder.adapterPosition].country)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is countryViewHolder){
            holder.bind(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    class countryViewHolder(val binding:ItemCountryListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:CountryListDataClass){
            binding.itemCountryListTvName.text=item.country
        }
    }
}