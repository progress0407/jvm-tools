package io.philo.spring_reactive.app

import org.springframework.data.annotation.Id


class Customer(val firstName: String, val lastName: String) {

    @Id
    var id: Long? = null

    override fun toString(): String {
        return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s']",
            id, firstName, lastName
        )
    }
}