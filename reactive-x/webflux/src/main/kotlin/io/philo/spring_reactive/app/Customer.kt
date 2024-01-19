package io.philo.spring_reactive.app

import org.springframework.data.annotation.Id


class Customer(val name: String) {

    @Id
    var id: Long? = null

    override fun toString(): String {
        return "Customer(name='$name', id=$id)"
    }
}
