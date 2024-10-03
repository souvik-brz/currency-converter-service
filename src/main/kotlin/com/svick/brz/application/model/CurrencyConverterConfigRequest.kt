package com.svick.brz.application.model

import com.svick.brz.respository.model.CurrencyConverterConfigEntity
import com.svick.brz.utils.DateTimeUtils.utcNow
import java.time.ZonedDateTime

data class CurrencyConverterConfigRequest(
    val id: Long = 0L,
    val currencyCode: String,
    val enabled: Boolean,
    val frequencyInMinutes: Int,
    val startDate: ZonedDateTime? = null,
    val endDate: ZonedDateTime? = null
) {
    fun toEntity() = CurrencyConverterConfigEntity(
        id = id,
        baseCode = currencyCode,
        enabled = enabled,
        frequencyInMinutes = frequencyInMinutes,
        startDate = startDate ?: utcNow(),
        endDate = endDate
    )
}
