package com.svick.brz.currencyconverter.service

import com.svick.brz.currencyconverter.application.CurrencyConverterApplication
import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfigRequest
import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfigResponse
import com.svick.brz.currencyconverter.config.exception.error.DuplicateResourceException
import com.svick.brz.currencyconverter.config.exception.error.ResourceNotFoundException
import com.svick.brz.currencyconverter.utils.utcNow
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
internal class CurrencyConverterConfigService(private val repositoryService: CurrencyConverterConfigRepositoryService) :
    CurrencyConverterApplication {
    override suspend fun createConfig(request: CurrencyConverterConfigRequest): CurrencyConverterConfigResponse {
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
        request: CurrencyConverterConfigRequest
    ): CurrencyConverterConfigResponse = repositoryService.byIdOrNull(id)?.let { existingEntity ->
        repositoryService.save(
            existingEntity.copy(
                baseCode = request.currencyCode,
                enabled = request.enabled,
                frequencyInMinutes = request.frequencyInMinutes,
                startDate = request.startDate ?: utcNow(),
                endDate = request.endDate
            )
        )
    }?.toModel() ?: throw ResourceNotFoundException(
        message = "Config not found with id - $id",
        cause = Throwable("UPDATE_CURRENCY_CONVERTER_CONFIG")
    )

    override suspend fun config(id: Long): CurrencyConverterConfigResponse =
        repositoryService.byIdOrNull(id)?.toModel()
            ?: throw ResourceNotFoundException(
                message = "Config not found with id - $id",
                cause = Throwable("GET_CURRENCY_CONVERTER_CONFIG")
            )

    override suspend fun config(currencyCode: String): CurrencyConverterConfigResponse =
        repositoryService.byCurrencyCode(currencyCode)?.toModel()
            ?: throw throw ResourceNotFoundException(
                message = "Config not found with currency code - $currencyCode",
                cause = Throwable("GET_CURRENCY_CONVERTER_CONFIG")
            )

    override suspend fun configs(pageable: Pageable): Page<CurrencyConverterConfigResponse> =
        repositoryService.findAll(pageable).map { it.toModel() }
}