package com.whysoezzy.exchangerates.core.mapper

interface ModelMapper<From, To> {
    fun fromTo(model: From): To
}