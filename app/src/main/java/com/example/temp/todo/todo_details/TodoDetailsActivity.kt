package com.example.temp.todo.todo_details

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.temp.databinding.ActivityTodoDetailsBinding

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