package com.whysoezzy.exchangerates.data

import com.whysoezzy.exchangerates.core.mapper.ModelMapper
import com.whysoezzy.exchangerates.data.model.Rates
import com.whysoezzy.exchangerates.data.model.ServerResponseDto

object ResponseDtoMapper : ModelMapper<ServerResponseDto, List<Rates>> {
    override fun fromTo(model: ServerResponseDto): List<Rates> {
        return model.rates.entries.map { entry ->
            Rates(
                name = entry.key,
                value = entry.value
            )
        }
    }
}
