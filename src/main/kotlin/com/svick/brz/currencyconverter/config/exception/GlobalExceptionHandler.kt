package com.svick.brz.currencyconverter.config.exception

import com.svick.brz.currencyconverter.config.exception.error.ClientApiException
import com.svick.brz.currencyconverter.config.exception.error.CurrencyNotSupportedException
import com.svick.brz.currencyconverter.config.exception.error.DuplicateResourceException
import com.svick.brz.currencyconverter.config.exception.error.ResourceNotFoundException
import com.svick.brz.currencyconverter.config.exception.model.ErrorResponse
import com.svick.brz.currencyconverter.config.logger.AppLogger
import com.svick.brz.currencyconverter.config.logger.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
internal class GlobalExceptionHandler(private val logger: AppLogger = AppLogger(className = GlobalExceptionHandler::class.java)) {

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
    internal suspend fun handleDuplicateResourceException(exception: DuplicateResourceException): ResponseEntity<ErrorResponse> =
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


    @ExceptionHandler(ClientApiException::class)
    internal suspend fun handleClientApiException(exception: ClientApiException): ResponseEntity<ErrorResponse> =
        HttpStatus.SERVICE_UNAVAILABLE.let {
            ResponseEntity(
                ErrorResponse(
                    statusCode = it.value(),
                    message = exception.localizedMessage,
                    progress = exception.cause?.message.toString()
                ),
                it
            )
        }

    @ExceptionHandler(CurrencyNotSupportedException::class)
    internal suspend fun handleCurrencyNotSupportedException(exception: CurrencyNotSupportedException): ResponseEntity<ErrorResponse> =
        HttpStatus.NOT_ACCEPTABLE.let {
            ResponseEntity(
                ErrorResponse(
                    statusCode = it.value(),
                    message = exception.localizedMessage,
                    progress = exception.cause?.message.toString()
                ),
                it
            )
        }

    private suspend fun <T> T.andLog(progress: String, message: String) = apply {
        logger.log(level = LogLevel.ERROR, progress = progress, message = message)
    }
}