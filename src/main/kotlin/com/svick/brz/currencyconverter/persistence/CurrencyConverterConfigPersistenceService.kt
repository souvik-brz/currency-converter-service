package com.svick.brz.currencyconverter.persistence

import com.svick.brz.currencyconverter.repository.CurrencyConverterConfigRepository
import com.svick.brz.currencyconverter.repository.model.CurrencyConverterConfigEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
internal class CurrencyConverterConfigPersistenceService(
    private val repository: CurrencyConverterConfigRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    internal suspend fun existsByCurrencyCode(currencyCode: String): Boolean = withContext(ioDispatcher) {
        repository.existsByBaseCode(currencyCode)
    }

    internal suspend fun save(currencyConverterConfigEntity: CurrencyConverterConfigEntity): CurrencyConverterConfigEntity =
        withContext(ioDispatcher) {
            repository.save(currencyConverterConfigEntity)
        }

    internal suspend fun byIdOrNull(id: Long): CurrencyConverterConfigEntity? = withContext(ioDispatcher) {
        repository.findByIdOrNull(id)
    }

    internal suspend fun byCurrencyCode(currencyCode: String): CurrencyConverterConfigEntity? =
        withContext(ioDispatcher) {
            repository.findByBaseCode(currencyCode)
        }

    internal suspend fun findAll(pageable: Pageable): Page<CurrencyConverterConfigEntity> = withContext(ioDispatcher) {
        repository.findAll(pageable)
    }

    internal suspend fun findAll(): List<CurrencyConverterConfigEntity> = withContext(ioDispatcher) {
        repository.findAllByParams()
    }
}