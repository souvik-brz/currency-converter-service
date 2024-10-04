package com.svick.brz.currencyconverter.service

import com.svick.brz.currencyconverter.application.CurrencyConverterApplication
import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfigRequest
import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfigResponse
import com.svick.brz.currencyconverter.config.logger.AppLogger
import com.svick.brz.currencyconverter.config.logger.LogLevel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
internal class CurrencyConverterConfigService(
//    private val repository: CurrencyConverterConfigRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val logger: AppLogger
) :
    CurrencyConverterApplication {
    override suspend fun createConfig(request: CurrencyConverterConfigRequest): CurrencyConverterConfigResponse {
        logger.log(LogLevel.INFO, progress = "CREATE_CONFIG", message = "Create config")
        TODO()
    }

    override suspend fun updateConfig(
        id: Long,
        request: CurrencyConverterConfigRequest
    ): CurrencyConverterConfigResponse {
        logger.log(LogLevel.INFO, progress = "UPDATE_CONFIG", message = "Update config")
        TODO()
    }

    override suspend fun config(id: Long): CurrencyConverterConfigResponse = TODO()

    override suspend fun config(currencyCode: String): CurrencyConverterConfigResponse = TODO()

    override suspend fun configs(pageable: Pageable): Page<CurrencyConverterConfigResponse> = TODO()
}