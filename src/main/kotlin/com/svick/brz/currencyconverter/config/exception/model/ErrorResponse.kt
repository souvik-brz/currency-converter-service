package com.svick.brz.currencyconverter.config.exception.model

internal data class ErrorResponse(val statusCode: Int, val message: String, val progress: String)
