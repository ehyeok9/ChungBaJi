package com.example.temp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.temp.databinding.ActivityRecomListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.util.*


class RecomListActivity : AppCompatActivity() {

    lateinit var recomListAdapter: RecomListAdapter
    lateinit var binding : ActivityRecomListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        // 텍스트 변경
        val country = intent.getStringExtra("country")
        binding.textView2.text = country

        var tag = "JP294232"
        if (country == "프랑스"){
            tag = "FR187070"
        } else if (country == "일본"){
            tag = "JP294232"
        } else if (country == "미국"){
            tag = "US191"
        }
        val list = doTask("https://travel.naver.com/overseas/" + tag + "/country/topPlace/tmap")

        // 리사이클러뷰
        recomListAdapter= RecomListAdapter(list)
        binding.rvRecomList.adapter=recomListAdapter
        recomListAdapter.notifyDataSetChanged()
    }

    private fun doTask(url: String) : ArrayList<checkboxData> {
        var itemList : ArrayList<checkboxData> = arrayListOf()
        CoroutineScope(Dispatchers.IO).launch {
            val document = Jsoup.connect(url).get()
            val elements : Elements = document.select("ul.mapView_list__108hK li")
            elements.forEach { element ->
                Log.d("test", "생성 중")
                var img = element.select("figure.topPlace_thumb__3Q3Hc img").attr("src")
                var spot = element.select("b.topPlace_name__1arme").text()
                var detail = element.select("span[class=topPlace_desc__rmZ30]").text()

                var item = checkboxData(img, spot, detail)
                itemList.add(item)


                val test: TimerTask = object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            recomListAdapter.notifyDataSetChanged()

                        }
                    }
                }
                test.run()

            }
            Log.d("test", "생성완료")

        }


        return itemList
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}