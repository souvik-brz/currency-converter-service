package com.svick.brz.currencyconverter.controller

import com.svick.brz.currencyconverter.application.CurrencyExchangeApplication
import com.svick.brz.currencyconverter.model.ExchangeCurrencyRequestV1
import com.svick.brz.currencyconverter.model.ExchangeCurrencyResponseV1
import com.svick.brz.currencyconverter.utils.ExchangeCurrencyObject.toDto
import com.svick.brz.currencyconverter.utils.ExchangeCurrencyObject.toModel
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange

@RestController
@RequestMapping("/v1/currency-exchange")
internal class CurrencyExchangeControllerV1(private val currencyExchangeApplication: CurrencyExchangeApplication) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    internal suspend fun exchangeCurrency(
        @Validated @RequestBody request: ExchangeCurrencyRequestV1,
        serverWebExchange: ServerWebExchange
    ): ExchangeCurrencyResponseV1 = currencyExchangeApplication.exchange(request.toModel()).toDto()
}