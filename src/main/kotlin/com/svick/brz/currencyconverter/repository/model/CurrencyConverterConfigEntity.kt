package com.svick.brz.currencyconverter.repository.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "currency_converter_config")
data class CurrencyConverterConfigEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val baseCode: String,
    val enabled: Boolean,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null,
    @CreationTimestamp
    @Column(insertable = false, updatable = false, nullable = false)
    val createdAt: ZonedDateTime,
    @UpdateTimestamp
    @Column(insertable = false)
    val modifiedAt: ZonedDateTime? = null
)
