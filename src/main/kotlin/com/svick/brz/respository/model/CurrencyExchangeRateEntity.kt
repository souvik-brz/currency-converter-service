package com.svick.brz.respository.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "currency_exchange_rates")
data class CurrencyExchangeRateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val result: String,
    val provider: String,
    val documentation: String,
    val termsOfUse: String,
    val timeLastUpdateUtc: ZonedDateTime,
    val timeNextUpdateUtc: ZonedDateTime,
    val baseCode: String,
    @CreationTimestamp
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @UpdateTimestamp
    val modifiedAt: ZonedDateTime? = null
)
