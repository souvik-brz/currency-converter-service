package com.svick.brz.currencyconverter.application.model

import java.time.ZonedDateTime

data class CurrencyExchangeRateConfig(
    val id: Long = 0L,
    val result: String,
    val documentation: String,
    val termsOfUse: String,
    val timeLastUpdateUtc: ZonedDateTime,
    val timeNextUpdateUtc: ZonedDateTime,
    val baseCode: String,
    val conversionRates: Map<String, Double>
)