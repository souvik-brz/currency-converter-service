package com.svick.brz.currencyconverter.utils

import com.svick.brz.currencyconverter.config.exception.error.ClientApiException
import io.github.resilience4j.retry.Retry
import io.github.resilience4j.retry.RetryConfig
import io.netty.handler.timeout.TimeoutException
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.time.Duration


object RetryUtils {

    fun <T> withRetry(
        maxAttempts: Int = 5,
        waitDuration: Long = 350L,
        failAfterMaxAttempts: Boolean = true,
        action: suspend () -> T
    ): T {
        val config = RetryConfig.custom<T>()
            .maxAttempts(maxAttempts)
            .waitDuration(Duration.ofMillis(waitDuration))
            .retryExceptions(ClientApiException::class.java, TimeoutException::class.java, IOException::class.java)
            .failAfterMaxAttempts(failAfterMaxAttempts)
            .build()

        val retry = Retry.of("webClientRetry", config)

        return retry.execute(action)
    }

    private fun <T> Retry.execute(action: suspend () -> T): T = Retry.decorateCheckedSupplier(this) {
        runBlocking { action() }
    }.get()
}