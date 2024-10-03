package com.svick.brz.controller

import com.svick.brz.model.ConfigRequestV1
import com.svick.brz.model.ConfigResponseV1
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange

@RestController
@RequestMapping("/v1/admin/currency-converter")
internal class CurrencyConverterAdminController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun createConfig(
        @Validated @RequestBody request: ConfigRequestV1,
        serverWebExchange: ServerWebExchange
    ): ResponseEntity<ConfigResponseV1> {
        TODO()
    }
}