package com.svick.brz.currencyconverter.config

import com.svick.brz.currencyconverter.job.ExchangeRateSchedulerJob
import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class QuartzConfig {

    @Bean
    fun jobDetail(): JobDetail {
        return JobBuilder.newJob(ExchangeRateSchedulerJob::class.java)
            .withIdentity("exchangeRateSchedulerJob")
            .storeDurably()
            .build()
    }

    @Bean
    fun trigger(jobDetail: JobDetail): Trigger {
        return TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity("exchangeRateSchedulerJobTrigger")
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInMinutes(1)
                    .repeatForever()
            )
            .build()
    }

}
