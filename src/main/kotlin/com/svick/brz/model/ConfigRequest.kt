package com.svick.brz.model

import java.time.ZonedDateTime

data class ConfigRequest(
    val id: Long = 0L,
    val currencyCode: String,
    val startDate: ZonedDateTime? = null,
    val endDate: ZonedDateTime? = null
)
