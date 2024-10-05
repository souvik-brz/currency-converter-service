package com.svick.brz

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.svick.brz.currencyconverter"])
@EnableAutoConfiguration
internal class CurrencyConverterServiceApplication

fun main(args: Array<String>) {
    runApplication<CurrencyConverterServiceApplication>(*args)
}
