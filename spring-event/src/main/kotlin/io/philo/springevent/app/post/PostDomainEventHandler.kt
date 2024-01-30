package io.philo.springevent.app.post

import io.philo.springevent.app.event.PreDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PostDomainEventHandler(private val postDomainService: PostDomainService) {

    @EventListener
    fun handleEvent(event: PreDomainEvent) {

        postDomainService.execute(event.preDomainId, event.percent, event.ex)
    }
}