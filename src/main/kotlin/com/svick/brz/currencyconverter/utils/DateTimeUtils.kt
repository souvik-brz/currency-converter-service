package com.svick.brz.currencyconverter.utils

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

fun utcNow(): ZonedDateTime = Instant.now().atZone(utcZone()).withNano(0)

fun utcZone(): ZoneId = ZoneId.of("UTC")