package com.whysoezzy.exchangerates.data.model

data class Rates(
    val id: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Int,
    val Name: String,
    val Value: Double,
    val Previous: Double
)