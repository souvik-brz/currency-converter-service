package com.svick.brz.currencyconverter.utils

import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfig
import com.svick.brz.currencyconverter.application.model.CurrencyExchangeRateConfig
import com.svick.brz.currencyconverter.application.model.ExchangeCurrency
import com.svick.brz.currencyconverter.client.model.ExchangeRateV6
import com.svick.brz.currencyconverter.model.CurrencyConverterConfigRequestV1
import com.svick.brz.currencyconverter.model.CurrencyConverterConfigResponseV1
import com.svick.brz.currencyconverter.model.ExchangeCurrencyRequestV1
import com.svick.brz.currencyconverter.model.ExchangeCurrencyResponseV1
import com.svick.brz.currencyconverter.repository.model.ConversionRateConfigEntity
import com.svick.brz.currencyconverter.repository.model.CurrencyConverterConfigEntity
import com.svick.brz.currencyconverter.repository.model.CurrencyExchangeRateConfigEntity
import java.time.Instant

object CurrencyConverterConfigObject {

    fun CurrencyConverterConfigRequestV1.toModel() = CurrencyConverterConfig(
        currencyCode = currencyCode,
        enabled = enabled,
        startDate = startDate ?: utcNow(),
        endDate = endDate
    )

    fun CurrencyConverterConfig.toEntity() = CurrencyConverterConfigEntity(
        id = id,
        baseCode = currencyCode,
        enabled = enabled,
        startDate = startDate,
        endDate = endDate,
        createdAt = utcNow()
    )

    fun CurrencyConverterConfig.toDto() = CurrencyConverterConfigResponseV1(
        id = id,
        currencyCode = currencyCode,
        enabled = enabled,
        startDate = startDate,
        endDate = endDate
    )

    fun CurrencyConverterConfigEntity.toModel() = CurrencyConverterConfig(
        id = id,
        currencyCode = baseCode,
        enabled = enabled,
        startDate = startDate,
        endDate = endDate,
        createdAt = createdAt,
        modifiedAt = modifiedAt
    )
}

object CurrencyExchangeRateConfigObject {

    fun CurrencyExchangeRateConfig.toEntity() = CurrencyExchangeRateConfigEntity(
        id = id,
        result = result,
        documentation = documentation,
        termsOfUse = termsOfUse,
        timeLastUpdateUtc = timeLastUpdateUtc,
        timeNextUpdateUtc = timeNextUpdateUtc,
        baseCode = baseCode,
        conversionRates = conversionRates.map {
            ConversionRateConfigEntity(currencyCode = it.key, rate = it.value)
        }
    )

    fun CurrencyExchangeRateConfigEntity.toModel() = CurrencyExchangeRateConfig(
        id = id,
        result = result,
        documentation = documentation,
        termsOfUse = termsOfUse,
        timeLastUpdateUtc = timeLastUpdateUtc,
        timeNextUpdateUtc = timeNextUpdateUtc,
        baseCode = baseCode,
        conversionRates = conversionRates.associate { it.currencyCode to it.rate }
    )

    fun ExchangeRateV6.toModel() = CurrencyExchangeRateConfig(
        id = 0,
        result = result,
        documentation = documentation,
        termsOfUse = termsOfUse,
        timeLastUpdateUtc = Instant.ofEpochMilli(timeLastUpdateUnix).atZone(utcZone()),
        timeNextUpdateUtc = Instant.ofEpochMilli(timeNextUpdateUnix).atZone(utcZone()),
        baseCode = baseCode,
        conversionRates = conversionRates
    )
}

object ExchangeCurrencyObject {
    fun ExchangeCurrencyRequestV1.toModel() = ExchangeCurrency(
        fromValue = fromValue,
        fromCurrencyCode = fromCurrencyCode,
        toValue = toValue,
        toCurrencyCode = toCurrencyCode
    )

    fun ExchangeCurrency.toDto() = ExchangeCurrencyResponseV1(
        fromValue = fromValue,
        fromCurrencyCode = fromCurrencyCode,
        toValue = toValue,
        toCurrencyCode = toCurrencyCode,
        termsOfUse = termsOfUse,
        exchangeRateLastUpdated = exchangeRateLastUpdated
    )
}