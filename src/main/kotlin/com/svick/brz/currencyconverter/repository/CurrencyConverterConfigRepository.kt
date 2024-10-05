package com.svick.brz.currencyconverter.repository

import com.svick.brz.currencyconverter.repository.model.CurrencyConverterConfigEntity
import com.svick.brz.currencyconverter.utils.utcNow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
interface CurrencyConverterConfigRepository : JpaRepository<CurrencyConverterConfigEntity, Long> {
    fun findByBaseCode(baseCode: String): CurrencyConverterConfigEntity?
    fun existsByBaseCode(baseCode: String): Boolean

    @Query(" SELECT c FROM CurrencyConverterConfigEntity c WHERE c.enabled IS TRUE AND c.startDate <= :startDate AND (c.endDate IS NULL OR c.endDate >= :endDate)")
    fun findAllByParams(
        startDate: ZonedDateTime = utcNow(),
        endDate: ZonedDateTime = utcNow()
    ): List<CurrencyConverterConfigEntity>
}