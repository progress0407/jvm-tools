package io.philo.app

import jakarta.persistence.*

@Table(name = "orders")
@Entity
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
}