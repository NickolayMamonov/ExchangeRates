package com.whysoezzy.exchangerates.data.model

import com.google.gson.annotations.SerializedName

data class ServerResponseDto (
    val disclaimer: String,
    val date: String,
    val timestamp: Int,
    val base: String,
    val rates: Map<String, Double>
)

