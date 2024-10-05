package com.svick.brz.currencyconverter.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
internal class WebClientConfig {

    @Value("\${app.config.exchange-rate-api-url}")
    private lateinit var exchangeRateApiUrl: String

    @Bean
    fun exchangeRateV6WebClient(): WebClient = WebClient.builder().baseUrl(exchangeRateApiUrl).build()
}