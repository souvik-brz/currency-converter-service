package com.svick.brz.utils

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

object DateTimeUtils {
    fun utcNow(): ZonedDateTime = Instant.now().atZone(ZoneId.of("UTC"))
}