package com.whysoezzy.exchangerates.utils

import com.whysoezzy.exchangerates.data.model.Rates
import com.whysoezzy.exchangerates.data.model.ServerResponseDto

object ResponseDtoMapper : ModelMapper<ServerResponseDto, List<Rates>> {


    override fun fromTo(model: ServerResponseDto): List<Rates> {
        return model.valute.entries.map { entry ->
            Rates(
                id = entry.value.id,
                charCode = entry.value.charCode,
                value = entry.value.value,
            )
        }
    }
}
