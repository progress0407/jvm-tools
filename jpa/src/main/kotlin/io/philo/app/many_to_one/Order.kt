package io.philo.app.many_to_one

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "orders")
class Order(name: String) {

    @Id
    @GeneratedValue
    var id: Long? = null
        protected set

    @Column
    var name: String = name

    protected constructor() : this("")
}