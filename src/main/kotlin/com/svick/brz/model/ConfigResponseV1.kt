package com.svick.brz.model

import java.time.ZonedDateTime

data class ConfigResponseV1(
    val id: Long = 0L,
    val currencyCode: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null
)
