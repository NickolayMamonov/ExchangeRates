package com.whysoezzy.exchangerates.data.model

data class ServerResponse(
    val disclaimer: String,
    val date: String,
    val timestamp: Int,
    val base: String,
    val rates: Map<String, Double>
)

