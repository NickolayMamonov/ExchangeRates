package com.whysoezzy.exchangerates.di

import com.whysoezzy.exchangerates.data.api.ApiService
import com.whysoezzy.exchangerates.presentation.conversion.vm.ConversionViewModel
import com.whysoezzy.exchangerates.presentation.main.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { ApiService.create() }
    viewModel {
        MainViewModel(get())
    }
    viewModel { parameters ->
        ConversionViewModel(parameters.get(), parameters.get())
    }
}