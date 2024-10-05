package com.svick.brz.currencyconverter.model

data class ExchangeCurrencyRequestV1(
    val fromValue: Double = 0.0,
    val fromCurrencyCode: String,
    val toValue: Double = 0.0,
    val toCurrencyCode: String
)
