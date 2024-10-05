package com.svick.brz.currencyconverter.model

import java.time.ZonedDateTime

data class CurrencyConverterConfigRequestV1(
    val currencyCode: String,
    val enabled: Boolean,
    val startDate: ZonedDateTime? = null,
    val endDate: ZonedDateTime? = null
)
