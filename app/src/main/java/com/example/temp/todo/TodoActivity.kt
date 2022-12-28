package com.example.temp.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.temp.R
import com.example.temp.databinding.ActivityTodoBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_todo.*

class TodoActivity : AppCompatActivity() {
    lateinit var todoDocFragment: TodoDocFragment
    lateinit var todoPackFragment: TodoPackFragment

    lateinit var binding: ActivityTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        todoDocFragment=TodoDocFragment()
        todoPackFragment=TodoPackFragment()

        supportFragmentManager.beginTransaction().add(R.id.frameLayout,todoDocFragment).commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        replaceView(todoDocFragment)
                    }
                    1 -> {
                        replaceView(todoPackFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }
    //화면변경
    private fun replaceView(tab:Fragment){
        var selectedFragment : Fragment? = null
        selectedFragment = tab
        selectedFragment.let{
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, it).commit()
        }
    }
}