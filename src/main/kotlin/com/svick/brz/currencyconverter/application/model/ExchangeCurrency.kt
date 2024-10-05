package com.svick.brz.currencyconverter.application.model

import java.time.ZonedDateTime

data class ExchangeCurrency(
    val fromValue: Double = 0.0,
    val fromCurrencyCode: String,
    val toValue: Double = 0.0,
    val toCurrencyCode: String,
    val termsOfUse: String? = null,
    val exchangeRateLastUpdated: ZonedDateTime? = null
)
