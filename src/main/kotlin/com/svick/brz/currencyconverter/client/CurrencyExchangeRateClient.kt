package com.svick.brz.currencyconverter.client

import com.svick.brz.currencyconverter.client.model.ExchangeRateV6
import com.svick.brz.currencyconverter.config.exception.error.ClientApiException
import com.svick.brz.currencyconverter.utils.RetryUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import org.springframework.web.reactive.function.client.awaitExchangeOrNull

@Service
class CurrencyExchangeRateClient(private val webClient: WebClient) {

    @Value("\${app.config.exchange-rate-api-key}")
    private lateinit var exchangeRateApiKey: String

    suspend fun exchangeRate(currencyCode: String): ExchangeRateV6 = RetryUtils.withRetry(
        action = {
            webClient.get().uri("/$exchangeRateApiKey/latest/$currencyCode").awaitExchangeOrNull { clientResponse ->
                if (clientResponse.statusCode().is2xxSuccessful) {
                    clientResponse.awaitBodyOrNull<ExchangeRateV6>()
                } else {
                    throw ClientApiException(
                        message = "Failed to fetch exchange rate for currency: $currencyCode, Status code: ${clientResponse.statusCode()}",
                        cause = Throwable("FETCH_EXCHANGE_RATE")
                    )
                }
            } ?: throw ClientApiException(message = "No body found", cause = Throwable("FETCH_EXCHANGE_RATE"))
        }
    )
}