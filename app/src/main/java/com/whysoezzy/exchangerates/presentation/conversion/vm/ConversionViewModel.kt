package com.whysoezzy.exchangerates.presentation.conversion.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConversionViewModel(name: String, private val value: Double) : ViewModel() {
    private val _dataState =
        MutableStateFlow(ConversionScreenState(name, value.toString(), ""))
    val dataState: StateFlow<ConversionScreenState> = _dataState

    fun convertRubToValute(rub: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val valute = value * rub
            val newState = dataState.value.copy(result = "$valute ${dataState.value.name}")
            _dataState.emit(newState)
        }

    }
}