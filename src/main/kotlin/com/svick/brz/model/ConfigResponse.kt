package com.svick.brz.model

import java.time.ZonedDateTime

data class ConfigResponse(
    val id: Long = 0L,
    val currencyCode: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime? = null
)
