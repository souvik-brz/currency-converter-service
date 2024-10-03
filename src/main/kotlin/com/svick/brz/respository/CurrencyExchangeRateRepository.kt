package com.svick.brz.respository

import com.svick.brz.respository.model.CurrencyExchangeRateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyExchangeRateRepository : JpaRepository<CurrencyExchangeRateEntity, Long> {
}