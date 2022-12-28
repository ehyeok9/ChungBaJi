package com.example.temp.todo.todo_doc_network

import com.example.temp.todo.models.passport.response.CountryPassPortResponse
import com.example.temp.todo.models.response.ContryEntryInfoResponse

interface TodoDocFragmentInterface {
    fun onContryEntryInfoSuccess(response:ContryEntryInfoResponse)
    fun onContryEntryInfoFailure(message:String,code:Int)

    fun onGetCountryPassPortSuccess(response:CountryPassPortResponse)
    fun onGetCountryPassPortFailure(message:String,code:Int)
}