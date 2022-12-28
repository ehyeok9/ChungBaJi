package com.example.temp.todo.todo_detais

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.temp.R
import com.example.temp.databinding.ActivityTodoBinding
import com.example.temp.databinding.ActivityTodoDetailsBinding
import com.example.temp.todo.TodoAdapter
import com.example.temp.todo.TodoDocFragment
import com.example.temp.todo.TodoPackFragment
import com.example.temp.todo.network.TodoService
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_todo.*

class TodoDetailsActivity: AppCompatActivity() {
    lateinit var binding: ActivityTodoDetailsBinding
    private var receiveIntent: Intent?=null
    private var content:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiveIntent=intent

        if(receiveIntent!=null){
            content= receiveIntent?.getStringExtra("content").toString()
        }

        binding.todoDetailsTvContent.text=content

    }
}