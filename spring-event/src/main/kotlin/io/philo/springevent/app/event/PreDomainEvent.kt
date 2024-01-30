package io.philo.springevent.app.event

import org.springframework.context.ApplicationEvent
import java.time.Clock

data class PreDomainEvent(val preDomainId: Long, val percent: Int) {
}