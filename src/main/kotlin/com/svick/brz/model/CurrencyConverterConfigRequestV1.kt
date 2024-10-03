package com.svick.brz.model

import com.svick.brz.application.model.CurrencyConverterConfigRequest
import java.time.ZonedDateTime

data class CurrencyConverterConfigRequestV1(
    val currencyCode: String,
    val enabled: Boolean,
    val frequencyInMinutes: Int,
    val startDate: ZonedDateTime? = null,
    val endDate: ZonedDateTime? = null
) {
    fun toModel() = CurrencyConverterConfigRequest(
        currencyCode = currencyCode,
        enabled = enabled,
        frequencyInMinutes = frequencyInMinutes,
        startDate = startDate,
        endDate = endDate
    )
}
