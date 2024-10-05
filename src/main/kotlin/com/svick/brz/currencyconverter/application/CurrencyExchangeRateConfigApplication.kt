package com.svick.brz.currencyconverter.application

import org.springframework.stereotype.Service

@Service
interface CurrencyExchangeRateConfigApplication {
    suspend fun createExchangeRateConfig(currencyCode: String)
}