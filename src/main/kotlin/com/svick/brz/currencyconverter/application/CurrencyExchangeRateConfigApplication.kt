package com.svick.brz.currencyconverter.application

import com.svick.brz.currencyconverter.application.model.CurrencyExchangeRateConfig
import org.springframework.stereotype.Service

@Service
interface CurrencyExchangeRateConfigApplication {
    suspend fun createExchangeRateConfig(currencyCode: String)
    suspend fun exchangeRate(fromCurrencyCode: String): CurrencyExchangeRateConfig?
}