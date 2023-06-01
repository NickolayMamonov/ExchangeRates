package com.whysoezzy.exchangerates.utils

interface ModelMapper<From,To> {
    fun fromTo(model:From): To
}

