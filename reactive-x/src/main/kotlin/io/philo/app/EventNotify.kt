package io.philo.app

import org.springframework.stereotype.Component

@Component
class EventNotify(
    val events: MutableList<String> = arrayListOf(),
    var change: Boolean = false
) {

    fun add(data: String) {
        events += data
        change = true
    }


}