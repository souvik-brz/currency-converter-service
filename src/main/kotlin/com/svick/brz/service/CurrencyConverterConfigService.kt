package com.svick.brz.service

import com.svick.brz.application.CurrencyConverterApplication
import com.svick.brz.application.model.CurrencyConverterConfigRequest
import com.svick.brz.application.model.CurrencyConverterConfigResponse
import com.svick.brz.respository.CurrencyConverterConfigRepository
import com.svick.brz.utils.DateTimeUtils.utcNow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
internal class CurrencyConverterConfigService(
    private val repository: CurrencyConverterConfigRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    CurrencyConverterApplication {
    override suspend fun createConfig(request: CurrencyConverterConfigRequest): CurrencyConverterConfigResponse {
        val existing = withContext(ioDispatcher) {
            repository.existsByBaseCode(request.currencyCode)
        }
        if (existing) {
            throw Exception("Need to be implemented")
        }
        return withContext(ioDispatcher) {
            repository.save(request.toEntity())
        }.toModel()
    }

    override suspend fun updateConfig(
        id: Long,
        request: CurrencyConverterConfigRequest
    ): CurrencyConverterConfigResponse {
        val existing = withContext(ioDispatcher) {
            repository.findByIdOrNull(id)
        } ?: throw Exception("Need to be implemented")

        return withContext(ioDispatcher) {
            repository.save(
                existing.copy(
                    enabled = request.enabled,
                    frequencyInMinutes = request.frequencyInMinutes,
                    startDate = request.startDate ?: utcNow(),
                    endDate = request.endDate
                )
            )
        }.toModel()
    }

    override suspend fun config(id: Long): CurrencyConverterConfigResponse = withContext(ioDispatcher) {
        repository.findByIdOrNull(id)
    }?.toModel() ?: throw Exception("Need to implemented")

    override suspend fun config(currencyCode: String): CurrencyConverterConfigResponse = withContext(ioDispatcher) {
        repository.findByBaseCode(currencyCode)
    }?.toModel() ?: throw Exception("Need to implemented")

    override suspend fun configs(pageable: Pageable): Page<CurrencyConverterConfigResponse> =
        withContext(ioDispatcher) {
            repository.findAll(pageable)
        }.map { it.toModel() }
}