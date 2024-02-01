package io.philo.springevent.app.post

import io.philo.springevent.app.event.PreDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class PostDomainEventHandler(private val postDomainService: PostDomainService) {

    @EventListener
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // default
    fun handleEvent(event: PreDomainEvent) {

        postDomainService.execute(event.preDomainId, event.percent, event.ex)
    }
}