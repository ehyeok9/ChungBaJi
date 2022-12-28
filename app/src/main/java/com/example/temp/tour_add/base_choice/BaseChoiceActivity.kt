package com.example.temp.tour_add.base_choice

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.temp.databinding.ActivityBaseChoiceBinding

class BaseChoiceActivity:AppCompatActivity() {
    private lateinit var binding: ActivityBaseChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityBaseChoiceBinding.inflate(layoutInflater); // 1
        setContentView(binding.root); //


    }
}