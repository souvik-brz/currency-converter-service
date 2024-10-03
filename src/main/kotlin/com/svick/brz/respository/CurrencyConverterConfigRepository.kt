package com.svick.brz.respository

import com.svick.brz.respository.model.CurrencyConverterConfigEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyConverterConfigRepository : JpaRepository<CurrencyConverterConfigEntity, Long> {
}