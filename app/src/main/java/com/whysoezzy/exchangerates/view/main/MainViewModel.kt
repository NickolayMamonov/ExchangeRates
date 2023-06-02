package com.whysoezzy.exchangerates.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whysoezzy.exchangerates.data.api.ApiService
import com.whysoezzy.exchangerates.data.model.Rates
import com.whysoezzy.exchangerates.utils.ResponseDtoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiService: ApiService = ApiService.create()
) : ViewModel() {
    private val _dataState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val dataState: StateFlow<MainScreenState> = _dataState
    var cache = emptyList<Rates>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getLatestCurrencies()
                val rates = ResponseDtoMapper.fromTo(response)
                cache = rates
                _dataState.emit(MainScreenState.Content(rates))
            } catch (th: Throwable) {
                _dataState.emit(MainScreenState.Error(th))
            }
        }
    }

    fun filterList(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredRates = cache.filter {
                it.charCode.contains(text, ignoreCase = true)
            }
            _dataState.emit(MainScreenState.Content(filteredRates))
        }

    }


}