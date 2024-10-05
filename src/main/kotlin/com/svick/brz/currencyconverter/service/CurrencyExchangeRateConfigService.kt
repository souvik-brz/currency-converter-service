package com.svick.brz.currencyconverter.service

import com.svick.brz.currencyconverter.application.CurrencyExchangeRateConfigApplication
import com.svick.brz.currencyconverter.application.model.CurrencyExchangeRateConfig
import com.svick.brz.currencyconverter.client.CurrencyExchangeRateClient
import com.svick.brz.currencyconverter.config.logger.AppLogger
import com.svick.brz.currencyconverter.config.logger.LogLevel
import com.svick.brz.currencyconverter.persistence.CurrencyExchangeRateConfigPersistenceService
import com.svick.brz.currencyconverter.repository.model.ConversionRateConfigEntity
import com.svick.brz.currencyconverter.utils.CurrencyExchangeRateConfigObject.toEntity
import com.svick.brz.currencyconverter.utils.CurrencyExchangeRateConfigObject.toModel
import org.springframework.stereotype.Service

@Service
class CurrencyExchangeRateConfigService(
    private val currencyExchangeRateClient: CurrencyExchangeRateClient,
    private val repositoryService: CurrencyExchangeRateConfigPersistenceService,
    private val logger: AppLogger = AppLogger(CurrencyExchangeRateConfigService::class.java)
) : CurrencyExchangeRateConfigApplication {

    override suspend fun createExchangeRateConfig(currencyCode: String) {
        val latestExchangeRate = currencyExchangeRateClient.exchangeRate(currencyCode).toModel()
        logger.log(
            level = LogLevel.INFO,
            progress = "FETCH_EXCHANGE_RATE",
            message = "Fetch Exchange Rate - $latestExchangeRate"
        )

        val existingExchangeRate = repositoryService.byBaseCode(currencyCode)

        if (existingExchangeRate != null) {
            repositoryService.save(existingExchangeRate.copy(
                result = latestExchangeRate.result,
                documentation = latestExchangeRate.documentation,
                termsOfUse = latestExchangeRate.termsOfUse,
                timeLastUpdateUtc = latestExchangeRate.timeLastUpdateUtc,
                timeNextUpdateUtc = latestExchangeRate.timeNextUpdateUtc,
                conversionRates = latestExchangeRate.conversionRates.map { latest ->
                    val existing = existingExchangeRate.conversionRates.firstOrNull { it.currencyCode == latest.key }
                    existing?.copy(rate = latest.value) ?: ConversionRateConfigEntity(
                        currencyCode = latest.key,
                        rate = latest.value
                    )
                }
            ))
        } else {
            repositoryService.save(latestExchangeRate.toEntity())
        }
    }

    override suspend fun exchangeRate(fromCurrencyCode: String): CurrencyExchangeRateConfig? =
        repositoryService.byBaseCode(fromCurrencyCode)?.toModel()
}