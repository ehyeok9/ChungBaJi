package com.example.temp.history.recycler

import android.graphics.drawable.Drawable

data class HistoryData(
    val img: Drawable?,
    val name: String,
    val startDate: String,
    val endDate: String
)
