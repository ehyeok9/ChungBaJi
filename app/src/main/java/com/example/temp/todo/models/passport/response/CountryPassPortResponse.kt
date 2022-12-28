package com.example.temp.todo.models.passport.response

data class CountryPassPortResponse(
    val currentCount: Int,
    val `data`: List<Data>,
    val numOfRows: Int,
    val pageNo: Int,
    val resultCode: Int,
    val resultMsg: String,
    val totalCount: Int
)