package io.philo.springevent.app.pre

import io.philo.springevent.app.event.PreDomainEvent
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PreDomainService(private val preDomainRepository: PreDomainRepository,
                       private val eventPublisher: ApplicationEventPublisher
) {

    private val log = KotlinLogging.logger { }

    @Transactional
    fun execute(id: Long, percent: Int, ex: Boolean) {

        val preEntity = preDomainRepository.findById(id).orElseThrow { IllegalArgumentException("Not Found Entity.") }
        preEntity.updateValueByPercent(percent)
//        preDomainRepository.save(preEntity)

        for (i in 1..1000) {
            log.info { "발행처 cnt = ${i}" }
            eventPublisher.publishEvent(PreDomainEvent(id, percent, ex))
        }
    }
}