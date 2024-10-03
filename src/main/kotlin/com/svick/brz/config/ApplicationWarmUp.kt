package com.svick.brz.config

import com.svick.brz.config.logger.AppLogger
import com.svick.brz.config.logger.LogLevel
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
internal class ApplicationWarmUp(private val logger: AppLogger) {

    @EventListener(ContextRefreshedEvent::class)
    suspend fun startUp() {
        logger.log(level = LogLevel.INFO, appProgressPoint = "APPLICATION_STARTUP", message = "Application Started")
    }

}