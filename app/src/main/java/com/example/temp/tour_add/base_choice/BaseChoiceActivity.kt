package com.example.temp.tour_add.base_choice

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.temp.databinding.ActivityBaseChoiceBinding
import com.example.temp.tour_add.country_choice.CountryChoiceActivity

class BaseChoiceActivity:AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityBaseChoiceBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityBaseChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener(){
        binding.baseChoiceImgBack.setOnClickListener{
            finish()
        }

        binding.baseChoiceTvDomestic.setOnClickListener {
            Toast.makeText(this@BaseChoiceActivity,"국내여행은 준비중입니다!",Toast.LENGTH_SHORT).show()
        }


        binding.baseChoiceTvForeign.setOnClickListener {
            var intent= Intent(this@BaseChoiceActivity,CountryChoiceActivity::class.java)
            startActivity(intent)

        }


    }
}