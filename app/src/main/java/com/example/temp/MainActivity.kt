package com.example.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.temp.databinding.ActivityMainBinding

import com.example.temp.home.FragmentHome

import com.example.temp.history.HistoryFragment

import com.example.temp.temp.Temp2Fragment
import com.example.temp.temp.TempFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().replace(R.id.main_frm, TempFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, FragmentHome())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_recommend -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, Temp2Fragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_my_history -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HistoryFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_btm_nav_home
        }
    }
}