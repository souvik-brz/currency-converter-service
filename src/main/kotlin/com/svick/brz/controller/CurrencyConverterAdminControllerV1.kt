package com.svick.brz.controller

import com.svick.brz.application.CurrencyConverterApplication
import com.svick.brz.model.CurrencyConverterConfigRequestV1
import com.svick.brz.model.CurrencyConverterConfigResponseV1
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange

@RestController
@RequestMapping("/v1/admin/currency-converter")
internal class CurrencyConverterAdminControllerV1(private val application: CurrencyConverterApplication) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun createConfig(
        @Validated @RequestBody request: CurrencyConverterConfigRequestV1,
        serverWebExchange: ServerWebExchange
    ): ResponseEntity<CurrencyConverterConfigResponseV1> {
        return ResponseEntity.ok(application.createConfig(request.toModel()).toDto())
    }
}