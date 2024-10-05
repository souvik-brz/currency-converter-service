package com.svick.brz.currencyconverter.controller

import com.svick.brz.currencyconverter.application.CurrencyConverterConfigApplication
import com.svick.brz.currencyconverter.model.CurrencyConverterConfigRequestV1
import com.svick.brz.currencyconverter.model.CurrencyConverterConfigResponseV1
import com.svick.brz.currencyconverter.utils.CurrencyConverterConfigObject.toDto
import com.svick.brz.currencyconverter.utils.CurrencyConverterConfigObject.toModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange

@RestController
@RequestMapping("/v1/admin/currency-converter")
internal class CurrencyConverterAdminControllerV1(private val application: CurrencyConverterConfigApplication) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    internal suspend fun createConfig(
        @Validated @RequestBody request: CurrencyConverterConfigRequestV1,
        serverWebExchange: ServerWebExchange
    ): ResponseEntity<CurrencyConverterConfigResponseV1> =
        ResponseEntity.ok(application.createConfig(request.toModel()).toDto())

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    internal suspend fun updateConfig(
        @PathVariable id: Long,
        @Validated @RequestBody request: CurrencyConverterConfigRequestV1,
        serverWebExchange: ServerWebExchange
    ): ResponseEntity<CurrencyConverterConfigResponseV1> =
        ResponseEntity.ok(application.updateConfig(id, request.toModel()).toDto())

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    internal suspend fun config(
        @PathVariable id: Long,
        serverWebExchange: ServerWebExchange
    ): ResponseEntity<CurrencyConverterConfigResponseV1> = ResponseEntity.ok(application.config(id).toDto())

    @GetMapping("/by-code")
    @ResponseStatus(HttpStatus.OK)
    internal suspend fun config(
        @RequestParam(value = "currencyCode", required = true) currencyCode: String,
        serverWebExchange: ServerWebExchange
    ): ResponseEntity<CurrencyConverterConfigResponseV1> = ResponseEntity.ok(application.config(currencyCode).toDto())

    @GetMapping(params = ["page", "size"])
    @ResponseStatus(HttpStatus.OK)
    internal suspend fun configs(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "20") size: Int,
        serverWebExchange: ServerWebExchange
    ): ResponseEntity<Page<CurrencyConverterConfigResponseV1>> =
        ResponseEntity.ok(application.configs(PageRequest.of(page, size)).map { it.toDto() })

}