package com.whysoezzy.exchangerates.view.main

import com.whysoezzy.exchangerates.data.model.Rates

sealed class MainScreenState{
    data class Content(val rates: List<Rates>) :MainScreenState()
    data class Error(val th: Throwable) : MainScreenState()
    object Loading : MainScreenState()
}