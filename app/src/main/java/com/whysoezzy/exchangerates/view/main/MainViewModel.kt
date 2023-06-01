package com.whysoezzy.exchangerates.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whysoezzy.exchangerates.data.api.ApiService
import com.whysoezzy.exchangerates.utils.ResponseDtoMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiService: ApiService = ApiService.create()
) : ViewModel() {
    private val _dataState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val dataState: StateFlow<MainScreenState> = _dataState

    init {
        viewModelScope.launch {
            try {
                val response = apiService.getLatestCurrencies()

                _dataState.emit(MainScreenState.Content(ResponseDtoMapper.fromTo(response)))
            } catch (th: Throwable) {
                _dataState.emit(MainScreenState.Error(th))
            }
        }
    }

}