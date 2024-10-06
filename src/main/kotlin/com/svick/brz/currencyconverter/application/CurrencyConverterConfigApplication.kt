package com.svick.brz.currencyconverter.application

import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfig
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface CurrencyConverterConfigApplication {
    suspend fun createConfig(request: CurrencyConverterConfig): CurrencyConverterConfig
    suspend fun updateConfig(id: Long, request: CurrencyConverterConfig): CurrencyConverterConfig
    suspend fun config(id: Long): CurrencyConverterConfig
    suspend fun config(currencyCode: String): CurrencyConverterConfig
    suspend fun activeConfigs(pageable: Pageable): Page<CurrencyConverterConfig>

    // This method is for internal use should not be exposed in rest api
    suspend fun activeConfigs(): List<CurrencyConverterConfig>
}