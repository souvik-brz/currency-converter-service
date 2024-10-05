package com.svick.brz.currencyconverter.repository

import com.svick.brz.currencyconverter.repository.model.CurrencyConverterConfigEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyConverterConfigRepository : JpaRepository<CurrencyConverterConfigEntity, Long> {
    fun findByBaseCode(baseCode: String): CurrencyConverterConfigEntity?
    fun existsByBaseCode(baseCode: String): Boolean
}