package com.svick.brz.currencyconverter.client

import com.svick.brz.currencyconverter.client.model.ExchangeRateV6
import com.svick.brz.currencyconverter.config.exception.error.ClientApiException
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import org.springframework.web.reactive.function.client.awaitExchangeOrNull

@Service
class CurrencyExchangeRateClient(private val webClient: WebClient) {

    suspend fun exchangeRate(currencyCode: String): ExchangeRateV6 =
        webClient.get().uri("/5329964c70888a69b136d8e4/latest/$currencyCode").awaitExchangeOrNull { clientResponse ->
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