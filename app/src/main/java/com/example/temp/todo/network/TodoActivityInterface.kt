package com.example.temp.todo.network

import com.example.temp.todo.models.response.ContryEntryInfoResponse

interface TodoActivityInterface {
    fun onContryEntryInfoSuccess(response:ContryEntryInfoResponse)
    fun onContryEntryInfoFailure(message:String,code:Int)
}