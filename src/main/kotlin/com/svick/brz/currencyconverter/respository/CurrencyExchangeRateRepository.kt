package com.svick.brz.currencyconverter.respository

import com.svick.brz.currencyconverter.respository.model.CurrencyExchangeRateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyExchangeRateRepository : JpaRepository<CurrencyExchangeRateEntity, Long> {
}