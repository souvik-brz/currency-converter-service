package com.svick.brz.service

import com.svick.brz.application.CurrencyConverterApplication
import com.svick.brz.model.ConfigRequest
import com.svick.brz.model.ConfigResponse
import com.svick.brz.respository.CurrencyConverterConfigRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
internal class CurrencyConverterConfigService(private val repository: CurrencyConverterConfigRepository) :
    CurrencyConverterApplication {
    override suspend fun createConfig(request: ConfigRequest): ConfigResponse {
        TODO("Not yet implemented")
    }

    override suspend fun updateConfig(id: Long, request: ConfigRequest): ConfigResponse {
        TODO("Not yet implemented")
    }

    override suspend fun config(id: Long): ConfigResponse {
        TODO("Not yet implemented")
    }

    override suspend fun configs(): Page<ConfigResponse> {
        TODO("Not yet implemented")
    }


}