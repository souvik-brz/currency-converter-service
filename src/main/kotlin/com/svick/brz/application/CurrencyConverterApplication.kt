package com.svick.brz.application

import com.svick.brz.model.ConfigRequest
import com.svick.brz.model.ConfigResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
interface CurrencyConverterApplication {
    suspend fun createConfig(request: ConfigRequest): ConfigResponse
    suspend fun updateConfig(id: Long, request: ConfigRequest): ConfigResponse
    suspend fun config(id: Long): ConfigResponse
    suspend fun configs(): Page<ConfigResponse>
}