package com.svick.brz.currencyconverter.application.model

import com.svick.brz.currencyconverter.model.CurrencyConverterConfigResponseV1
import java.time.ZonedDateTime

data class CurrencyConverterConfigResponse(
    val id: Long = 0L,
    val currencyCode: String,
    val enabled: Boolean,
    val frequencyInMinutes: Int,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime? = null
) {
    fun toDto() = CurrencyConverterConfigResponseV1(
        id = id,
        currencyCode = currencyCode,
        enabled = enabled,
        frequencyInMinutes = frequencyInMinutes,
        startDate = startDate,
        endDate = endDate
    )
}
