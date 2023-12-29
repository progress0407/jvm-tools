package io.philo.app.reactive

class _ReactiveMain

fun main() {
    val publisher = MyPublisher()
    val subscriber = MySubscriber()

    publisher.subscribe(subscriber)
}