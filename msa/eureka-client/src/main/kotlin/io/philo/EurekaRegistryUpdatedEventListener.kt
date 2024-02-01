package io.philo

import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class EurekaRegistryUpdatedEventListener {

    private val log = KotlinLogging.logger { }

    @EventListener
    fun handEvent(event: EurekaRegistryUpdatedEvent) {

        log.info { "event: $event" }
    }
}