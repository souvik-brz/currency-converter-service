package com.svick.brz.currencyconverter.application

import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfigRequest
import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfigResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface CurrencyConverterApplication {
    suspend fun createConfig(request: CurrencyConverterConfigRequest): CurrencyConverterConfigResponse
    suspend fun updateConfig(id: Long, request: CurrencyConverterConfigRequest): CurrencyConverterConfigResponse
    suspend fun config(id: Long): CurrencyConverterConfigResponse
    suspend fun config(currencyCode: String): CurrencyConverterConfigResponse
    suspend fun configs(pageable: Pageable): Page<CurrencyConverterConfigResponse>
}