package com.whysoezzy.exchangerates.presentation.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whysoezzy.exchangerates.data.ResponseDtoMapper
import com.whysoezzy.exchangerates.data.api.ApiService
import com.whysoezzy.exchangerates.data.model.Rates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiService: ApiService
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
                it.name.contains(text, ignoreCase = true)
            }
            _dataState.emit(MainScreenState.Content(filteredRates))
        }

    }


}