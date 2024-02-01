package io.philo.app

import io.philo.EurekaRegistryUpdatedEvent
import mu.KotlinLogging
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class EurekaEventListener(private val eventPublisher: ApplicationEventPublisher) {

    private val log = KotlinLogging.logger { }

    @EventListener
    fun handEvent(event: EurekaInstanceRegisteredEvent) {

        log.info { "event: $event" }

        eventPublisher.publishEvent(EurekaRegistryUpdatedEvent())
    }
}