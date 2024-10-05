package com.svick.brz.currencyconverter.repository.model

import com.svick.brz.currencyconverter.utils.utcNow
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
@Table(name = "conversion_rate")
data class ConversionRateConfigEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val currencyCode: String,
    @Column(precision = 65, scale = 30)
    val rate: BigDecimal,
    @CreationTimestamp
    @Column(insertable = false, updatable = false, nullable = false)
    val createdAt: ZonedDateTime = utcNow(),
    @UpdateTimestamp
    @Column(insertable = false)
    val modifiedAt: ZonedDateTime? = null
)
