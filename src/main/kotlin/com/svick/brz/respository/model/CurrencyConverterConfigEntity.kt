package com.svick.brz.respository.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "currency_converter_configs")
data class CurrencyConverterConfigEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val baseCode: String,
    val enabled: Boolean,
    val frequencyInMinutes: Int,
    @CreationTimestamp
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @UpdateTimestamp
    val modifiedAt: ZonedDateTime? = null
)
