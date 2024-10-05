package com.svick.brz.currencyconverter.repository

import com.svick.brz.currencyconverter.repository.model.CurrencyExchangeRateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyExchangeRateRepository : JpaRepository<CurrencyExchangeRateEntity, Long> {
}