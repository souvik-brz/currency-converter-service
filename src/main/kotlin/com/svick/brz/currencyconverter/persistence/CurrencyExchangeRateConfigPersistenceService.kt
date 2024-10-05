package com.svick.brz.currencyconverter.persistence

import com.svick.brz.currencyconverter.repository.CurrencyExchangeRateRepository
import com.svick.brz.currencyconverter.repository.model.CurrencyExchangeRateConfigEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class CurrencyExchangeRateConfigPersistenceService(
    private val repository: CurrencyExchangeRateRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    internal suspend fun save(entity: CurrencyExchangeRateConfigEntity): CurrencyExchangeRateConfigEntity =
        withContext(ioDispatcher) {
            repository.save(entity)
        }

    internal suspend fun byBaseCode(baseCode: String): CurrencyExchangeRateConfigEntity? = withContext(ioDispatcher) {
        repository.findByBaseCode(baseCode)
    }
}