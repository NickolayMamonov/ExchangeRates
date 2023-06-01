package com.whysoezzy.exchangerates.data.model

data class ServerResponse(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val rates: Map<String, Rates>
)
