package com.whysoezzy.exchangerates.view.conversion

import com.whysoezzy.exchangerates.data.model.RatesDto

sealed class ConversionScreenState{
    data class Content(val id: RatesDto) : ConversionScreenState()
    data class Error(val th: Throwable) : ConversionScreenState()
    object Loading : ConversionScreenState()
}