package com.whysoezzy.exchangerates.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ServerResponseDto(
    @SerializedName("Date")
    val date: Date,

    @SerializedName("PreviousDate")
    val previousDate: Date,

    @SerializedName("PreviousURL")
    val previousURL: String,

    @SerializedName("Timestamp")
    val timestamp: Date,

    @SerializedName("Valute")
    val valute: Map<String, RatesDto>
)

