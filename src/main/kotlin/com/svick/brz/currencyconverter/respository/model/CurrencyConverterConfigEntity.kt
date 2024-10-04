package com.svick.brz.currencyconverter.respository.model

import com.svick.brz.currencyconverter.application.model.CurrencyConverterConfigResponse
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
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null,
    @CreationTimestamp
    @Column(insertable = false, updatable = false, nullable = false)
    val createdAt: ZonedDateTime,
    @UpdateTimestamp
    @Column(insertable = false, updatable = false)
    val modifiedAt: ZonedDateTime? = null
) {
    fun toModel() = CurrencyConverterConfigResponse(
        id = id,
        currencyCode = baseCode,
        enabled = enabled,
        frequencyInMinutes = frequencyInMinutes,
        startDate = startDate,
        endDate = endDate,
        createdAt = createdAt,
        modifiedAt = modifiedAt
    )
}
