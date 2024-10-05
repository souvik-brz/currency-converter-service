package com.svick.brz.currencyconverter.repository.model

import com.svick.brz.currencyconverter.utils.utcNow
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "currency_exchange_rate")
data class CurrencyExchangeRateConfigEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val result: String,
    val documentation: String,
    val termsOfUse: String,
    val timeLastUpdateUtc: ZonedDateTime,
    val timeNextUpdateUtc: ZonedDateTime,
    val baseCode: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_exchange_rate_id", referencedColumnName = "id", nullable = false, updatable = false)
    val conversionRates: List<ConversionRateConfigEntity>,
    @CreationTimestamp
    @Column(insertable = false, updatable = false, nullable = false)
    val createdAt: ZonedDateTime = utcNow(),
    @UpdateTimestamp
    @Column(insertable = false)
    val modifiedAt: ZonedDateTime? = null
)
