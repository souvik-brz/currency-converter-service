package com.svick.brz.respository.model

import com.svick.brz.application.model.CurrencyConverterConfigResponse
import com.svick.brz.utils.DateTimeUtils.utcNow
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
    val startDate: ZonedDateTime = utcNow(),
    val endDate: ZonedDateTime? = null,
    @CreationTimestamp
    val createdAt: ZonedDateTime = utcNow(),
    @UpdateTimestamp
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
