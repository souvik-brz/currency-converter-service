package com.svick.brz

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.svick.brz.currencyconverter"])
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = ["com.svick.brz.currencyconverter.repository"])
internal class CurrencyConverterServiceApplication

fun main(args: Array<String>) {
    runApplication<CurrencyConverterServiceApplication>(*args)
}
