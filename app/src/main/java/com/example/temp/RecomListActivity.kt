package com.example.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.SingleRequest
import com.example.temp.databinding.ActivityRecomListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements


class RecomListActivity : AppCompatActivity() {

    lateinit var recomListAdapter: RecomListAdapter
    lateinit var binding : ActivityRecomListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 텍스트 변경
        val country = intent.getStringExtra("country")
        binding.textView2.text = country
        Log.d("test", "생성")

        val list = doTask("https://travel.naver.com/overseas/JP294232/country/topPlace/tspot")

//        Log.d("test", list.get(0).title)
        // 리사이클러뷰
        recomListAdapter= RecomListAdapter(list)
        binding.rvRecomList.adapter=recomListAdapter
        recomListAdapter.notifyDataSetChanged()
    }

    private fun doTask(url: String) : ArrayList<checkboxData> {
        var itemList : ArrayList<checkboxData> = arrayListOf()

        CoroutineScope(Dispatchers.IO).launch {
            val document = Jsoup.connect(url).get()
            val elements : Elements = document.select("ul[class=topPlaceSpot_list__1l27A]").select("li")

            elements.forEach { element ->
                var img = element.select("figure[class=poiItem_thumb__1JJu8]").select("img").attr("src")
                var spot = element.select("figure[class=poiItem_thumb__1JJu8]").select("img").attr("alt")
                var detail = element.select("span[class=poiItem_desc__31sC4]").text()

                var item = checkboxData(img, spot, detail)
                itemList.add(item)
            }
        }

        return itemList
    }
}