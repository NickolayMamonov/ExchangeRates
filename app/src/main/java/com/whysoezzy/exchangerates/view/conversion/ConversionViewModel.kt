package com.whysoezzy.exchangerates.view.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConversionViewModel: ViewModel() {
    private val _name = MutableLiveData<String>()
    private val _value = MutableLiveData<Double>()

    val name: LiveData<String>
        get() = _name
    val value: LiveData<Double>
        get() = _value

    fun init(charCode: String, value: Double) {
        _name.value = charCode
        _value.value = value
    }

    fun convertRubToValute(rub: Int): String {
        val valute = value.value ?: return ""
        val name = name.value ?: return ""
        val result = rub * valute
        return "${result.toInt()} $name"
    }
}