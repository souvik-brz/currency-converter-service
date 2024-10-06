package com.svick.brz.currencyconverter.service

import com.svick.brz.currencyconverter.application.CurrencyExchangeApplication
import com.svick.brz.currencyconverter.application.CurrencyExchangeRateConfigApplication
import com.svick.brz.currencyconverter.application.model.ExchangeCurrency
import com.svick.brz.currencyconverter.config.exception.error.CurrencyNotSupportedException
import org.springframework.stereotype.Service

@Service
internal class CurrencyExchangeService(private val exchangeRateConfigApplication: CurrencyExchangeRateConfigApplication) :
    CurrencyExchangeApplication {

    override suspend fun exchange(request: ExchangeCurrency): ExchangeCurrency {
        val exchangeRates = exchangeRateConfigApplication.exchangeRate(request.fromCurrencyCode)
        val exchangeRate =
            exchangeRates?.conversionRates?.entries?.firstOrNull { it.key == request.toCurrencyCode }?.value
                ?: throw CurrencyNotSupportedException(
                    "Currency code - ${request.fromCurrencyCode} or ${request.toCurrencyCode} not supported",
                    null
                )
        return request.copy(
            toValue = request.fromValue * exchangeRate,
            termsOfUse = exchangeRates.termsOfUse,
            exchangeRateLastUpdated = exchangeRates.timeLastUpdateUtc
        )
    }
}