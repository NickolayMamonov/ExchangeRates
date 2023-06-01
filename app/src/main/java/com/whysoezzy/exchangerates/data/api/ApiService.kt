package com.whysoezzy.exchangerates.data.api

import com.whysoezzy.exchangerates.data.model.Rates
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("daily_json.js")
    suspend fun getLatestCurrencies(
    ): List<Rates>


    companion object {

        private const val BASE_URL = "https://www.cbr-xml-daily.ru/"

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)

        }
    }
}