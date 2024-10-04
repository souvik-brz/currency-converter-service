package com.svick.brz.currencyconverter.model

import java.time.ZonedDateTime

data class CurrencyConverterConfigResponseV1(
    val id: Long = 0L,
    val currencyCode: String,
    val enabled: Boolean,
    val frequencyInMinutes: Int,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null
)
