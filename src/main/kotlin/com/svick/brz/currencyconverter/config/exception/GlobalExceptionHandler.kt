package com.svick.brz.currencyconverter.config.exception

import com.svick.brz.currencyconverter.config.exception.error.DuplicateResourceException
import com.svick.brz.currencyconverter.config.exception.error.ResourceNotFoundException
import com.svick.brz.currencyconverter.config.exception.model.ErrorResponse
import com.svick.brz.currencyconverter.config.logger.AppLogger
import com.svick.brz.currencyconverter.config.logger.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
internal class GlobalExceptionHandler(private val logger: AppLogger) {

    @ExceptionHandler(ResourceNotFoundException::class)
    internal suspend fun handleResourceNotFoundException(exception: ResourceNotFoundException): ResponseEntity<ErrorResponse> =
        HttpStatus.NOT_FOUND.let {
            ResponseEntity(
                ErrorResponse(
                    statusCode = it.value(),
                    message = exception.localizedMessage,
                    progress = exception.cause?.message.toString()
                ),
                it
            )
        }.andLog(progress = exception.cause?.message.toString(), message = exception.localizedMessage)

    @ExceptionHandler(DuplicateResourceException::class)
    internal suspend fun handleDuplicateResourceException(exception: ResourceNotFoundException): ResponseEntity<ErrorResponse> =
        HttpStatus.BAD_REQUEST.let {
            ResponseEntity(
                ErrorResponse(
                    statusCode = it.value(),
                    message = exception.localizedMessage,
                    progress = exception.cause?.message.toString()
                ),
                it
            )
        }.andLog(progress = exception.cause?.message.toString(), message = exception.localizedMessage)

    private suspend fun <T> T.andLog(progress: String, message: String) = apply {
        logger.log(level = LogLevel.ERROR, progress = progress, message = message)
    }
}