package com.svick.brz.currencyconverter.application

import com.svick.brz.currencyconverter.application.model.ExchangeCurrency
import org.springframework.stereotype.Service

@Service
fun interface CurrencyExchangeApplication {
    suspend fun exchange(request: ExchangeCurrency): ExchangeCurrency
}