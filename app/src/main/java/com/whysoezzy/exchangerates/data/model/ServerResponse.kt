package com.whysoezzy.exchangerates.data.model

import java.util.Date

data class ServerResponse(
    val date: Date,
    val previousDate: String,
    val previousURL: String,
    val timestamp: Int,
    val valute: Map<String, RatesDto>
)

