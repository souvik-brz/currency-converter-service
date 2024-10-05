package com.svick.brz.currencyconverter.service

import com.svick.brz.currencyconverter.application.CurrencyExchangeApplication
import com.svick.brz.currencyconverter.application.CurrencyExchangeRateConfigApplication
import com.svick.brz.currencyconverter.application.model.ExchangeCurrency
import org.springframework.stereotype.Service

@Service
internal class CurrencyExchangeService(private val exchangeRateConfigApplication: CurrencyExchangeRateConfigApplication) :
    CurrencyExchangeApplication {

    override suspend fun exchange(request: ExchangeCurrency): ExchangeCurrency {
        val exchangeRates = exchangeRateConfigApplication.exchangeRate(request.fromCurrencyCode)
        val exchangeRate =
            exchangeRates?.conversionRates?.entries?.firstOrNull { it.key == request.toCurrencyCode }?.value ?: 0.0
        return request.copy(
            toValue = request.fromValue * exchangeRate,
            termsOfUse = exchangeRates?.termsOfUse,
            exchangeRateLastUpdated = exchangeRates?.timeLastUpdateUtc
        )
    }
}