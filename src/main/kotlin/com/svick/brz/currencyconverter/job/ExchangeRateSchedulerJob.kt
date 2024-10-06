package com.svick.brz.currencyconverter.job

import com.svick.brz.currencyconverter.application.CurrencyConverterConfigApplication
import com.svick.brz.currencyconverter.application.CurrencyExchangeRateConfigApplication
import com.svick.brz.currencyconverter.config.logger.AppLogger
import com.svick.brz.currencyconverter.config.logger.LogLevel
import kotlinx.coroutines.runBlocking
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
internal class ExchangeRateSchedulerJob(
    private val currencyConverterConfigApplication: CurrencyConverterConfigApplication,
    private val currencyExchangeRateConfigApplication: CurrencyExchangeRateConfigApplication,
    private val logger: AppLogger = AppLogger(className = ExchangeRateSchedulerJob::class.java)
) : Job {

    @Value("\${app.config.quartz-scheduler-enabled}")
    private lateinit var scheduledEnabled: String

    override fun execute(context: JobExecutionContext?) {
        runBlocking {
            try {
                if (scheduledEnabled.toBooleanStrict()) {
                    logger.log(
                        level = LogLevel.INFO,
                        progress = "START_EXCHANGE_RATE_JOB",
                        message = "Exchange Rate Job Started"
                    )
                    currencyConverterConfigApplication.activeConfigs().takeIf { it.isNotEmpty() }?.map {
                        currencyExchangeRateConfigApplication.createExchangeRateConfig(it.currencyCode)
                    }

                    logger.log(
                        level = LogLevel.INFO,
                        progress = "COMPLETED_EXCHANGE_RATE_JOB",
                        message = "Exchange Rate Job Completed"
                    )
                } else {
                    logger.log(
                        level = LogLevel.INFO,
                        progress = "SKIPPED_EXCHANGE_RATE_JOB",
                        message = "Exchange Rate Job Skipped"
                    )
                }
            } catch (ex: Exception) {
                logger.log(
                    level = LogLevel.ERROR,
                    progress = "EXCHANGE_RATE_JOB_FAILED",
                    message = "Exchange rate job failed to start",
                    throwable = ex.cause
                )
            }
        }
    }

}