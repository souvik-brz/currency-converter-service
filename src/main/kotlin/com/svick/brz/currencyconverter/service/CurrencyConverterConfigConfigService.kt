package com.svick.brz.currencyconverter.service

import com.svick.brz.currencyconverter.application.CurrencyConverterConfigApplication
import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfig
import com.svick.brz.currencyconverter.config.exception.error.DuplicateResourceException
import com.svick.brz.currencyconverter.config.exception.error.ResourceNotFoundException
import com.svick.brz.currencyconverter.config.logger.AppLogger
import com.svick.brz.currencyconverter.config.logger.LogLevel
import com.svick.brz.currencyconverter.persistence.CurrencyConverterConfigPersistenceService
import com.svick.brz.currencyconverter.utils.CurrencyConverterConfigObject.toEntity
import com.svick.brz.currencyconverter.utils.CurrencyConverterConfigObject.toModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
internal class CurrencyConverterConfigConfigService(
    private val repositoryService: CurrencyConverterConfigPersistenceService,
    private val logger: AppLogger = AppLogger(className = CurrencyConverterConfigConfigService::class.java)
) :
    CurrencyConverterConfigApplication {
    override suspend fun createConfig(request: CurrencyConverterConfig): CurrencyConverterConfig {
        val isExisting = repositoryService.existsByCurrencyCode(request.currencyCode)

        if (isExisting) {
            throw DuplicateResourceException(
                message = "Resource already exists with currency code - ${request.currencyCode}",
                cause = Throwable("CREATE_CURRENCY_CONVERTER_CONFIG")
            )
        }

        return repositoryService.save(request.toEntity()).toModel()
    }

    override suspend fun updateConfig(
        id: Long,
        request: CurrencyConverterConfig
    ): CurrencyConverterConfig = repositoryService.byIdOrNull(id)?.let { existingEntity ->
        repositoryService.save(
            existingEntity.copy(
                baseCode = request.currencyCode,
                enabled = request.enabled,
                startDate = request.startDate,
                endDate = request.endDate
            )
        )
    }?.toModel() ?: throw ResourceNotFoundException(
        message = "Config not found with id - $id",
        cause = Throwable("UPDATE_CURRENCY_CONVERTER_CONFIG")
    )

    override suspend fun config(id: Long): CurrencyConverterConfig =
        repositoryService.byIdOrNull(id)?.toModel()
            ?: throw ResourceNotFoundException(
                message = "Config not found with id - $id",
                cause = Throwable("GET_CURRENCY_CONVERTER_CONFIG")
            )

    override suspend fun config(currencyCode: String): CurrencyConverterConfig =
        repositoryService.byCurrencyCode(currencyCode)?.toModel()
            ?: throw throw ResourceNotFoundException(
                message = "Config not found with currency code - $currencyCode",
                cause = Throwable("GET_CURRENCY_CONVERTER_CONFIG")
            )

    override suspend fun configs(pageable: Pageable): Page<CurrencyConverterConfig> =
        repositoryService.findAll(pageable).map { it.toModel() }

    override suspend fun configs(): List<CurrencyConverterConfig> =
        repositoryService.findAll().map { it.toModel() }.also {
            logger.log(
                level = LogLevel.INFO,
                progress = "FETCH_CONFIGS",
                message = "Fetch all enabled configs $it"
            )
        }
}