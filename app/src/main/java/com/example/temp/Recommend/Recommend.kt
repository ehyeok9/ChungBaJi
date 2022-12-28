package com.example.temp.Recommend

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.temp.R
import com.example.temp.databinding.RecommendBinding


class Recommend : AppCompatActivity() {
    private lateinit var binding: RecommendBinding
    private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
    private val MIN_ALPHA = 0.5f // 어두워지는 정도를 나타낸 듯 하다.

    override fun onCreate(savedInstanceState: Bundle?) {
        // 뷰 바인딩
        super.onCreate(savedInstanceState)
        binding = RecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴 바
        val toolbar = binding.RecommendToolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)


        // 뷰 페이저
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // dimen 파일 안에 크기를 정의해두었다.
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth) // dimen 파일이 없으면 생성해야함
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        val viewPager_pic = binding.viewPagerPic
        viewPager_pic.offscreenPageLimit = 1
        viewPager_pic.adapter = RecommendViewPagerAdapter(getPicList())
        viewPager_pic.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager_pic.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

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

    private fun getPicList() : ArrayList<ViewPagerData> {
        val itemList = arrayListOf<ViewPagerData>(
            ViewPagerData(R.drawable.france, "프랑스", "낭만이 가득한 나라"),
            ViewPagerData(R.drawable.america, "미국", "가장 자유로운 나라"),
            ViewPagerData(R.drawable.japan, "일본", "시대가 공존하는 나라"),
        )
        return itemList
    }
}