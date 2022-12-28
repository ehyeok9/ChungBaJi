package com.example.temp.Recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.temp.R
import com.example.temp.databinding.FragmentRecommendBinding
import com.example.temp.databinding.RecommendBinding

class RecommendFragment : Fragment() {

    private lateinit var binding: FragmentRecommendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_recommend, container, false)
        binding = FragmentRecommendBinding.inflate(layoutInflater)

        // 툴 바
        val toolbar = binding.RecommendToolbar
//        setSupportActionBar(toolbar)
//        val ab = supportActionBar!!
//        ab.setDisplayShowTitleEnabled(false)
//        ab.setDisplayHomeAsUpEnabled(true)


        // 뷰 페이저
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // dimen 파일 안에 크기를 정의해두었다.
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth) // dimen 파일이 없으면 생성해야함
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        val viewPager_pic = rootView.findViewById<ViewPager2>(R.id.viewPager_pic)
        viewPager_pic.offscreenPageLimit = 1
        viewPager_pic.adapter = RecommendViewPagerAdapter(getPicList())
        viewPager_pic.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager_pic.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
        return rootView
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun getPicList() : ArrayList<ViewPagerData> {
        val itemList = arrayListOf<ViewPagerData>(
            ViewPagerData(R.drawable.france, "프랑스", "낭만이 가득한 나라"),
            ViewPagerData(R.drawable.america, "미국", "가장 자유로운 나라"),
            ViewPagerData(R.drawable.japan, "일본", "해가 먼저뜨는 나라"),
        )
        return itemList
    }
}