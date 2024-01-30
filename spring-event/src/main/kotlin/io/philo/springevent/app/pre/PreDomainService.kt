package io.philo.springevent.app.pre

import io.philo.springevent.app.event.PreDomainEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PreDomainService(private val preDomainRepository: PreDomainRepository,
                       private val eventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun execute(id: Long, percent: Int) {

        val preEntity = preDomainRepository.findById(id).orElseThrow { IllegalArgumentException("Not Found Entity.") }
        preEntity.updateValueByPercent(percent)
        eventPublisher.publishEvent(PreDomainEvent(id, percent))
    }
}