package com.svick.brz.config.logger

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
internal class AppLogger(
    className: Class<*> = AppLogger::class.java,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val logger: Logger = LoggerFactory.getLogger(className)
) {

    suspend fun <T> log(
        level: LogLevel,
        appProgressPoint: String,
        message: T,
        throwable: Throwable? = null,
    ) {
        withContext(ioDispatcher) {
            val logMessage = message.toString()
            val finalMessage = "[$appProgressPoint] - $logMessage"

            when (level) {
                LogLevel.INFO -> logger.info(finalMessage)
                LogLevel.DEBUG -> logger.debug(finalMessage)
                LogLevel.WARN -> logger.warn(finalMessage)
                LogLevel.ERROR -> throwable?.let { logger.error(finalMessage, it) } ?: logger.error(finalMessage)
            }
        }
    }
}