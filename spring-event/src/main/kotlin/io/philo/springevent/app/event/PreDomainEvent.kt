package io.philo.springevent.app.event

data class PreDomainEvent(val preDomainId: Long, val percent: Int, val ex: Boolean) {
}