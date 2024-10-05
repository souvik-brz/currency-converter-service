package com.svick.brz.currencyconverter.application.model

import com.svick.brz.currencyconverter.utils.utcNow
import java.time.ZonedDateTime

data class CurrencyConverterConfig(
    val id: Long = 0L,
    val currencyCode: String,
    val enabled: Boolean,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null,
    val createdAt: ZonedDateTime = utcNow(),
    val modifiedAt: ZonedDateTime? = null
)
