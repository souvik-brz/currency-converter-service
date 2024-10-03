package com.svick.brz.model

import java.time.ZonedDateTime

data class ConfigRequestV1(
    val currencyCode: String,
    val startDate: ZonedDateTime? = null,
    val endDate: ZonedDateTime? = null
)
