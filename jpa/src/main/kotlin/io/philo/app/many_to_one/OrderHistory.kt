package io.philo.app.many_to_one

import jakarta.persistence.*
import jakarta.persistence.CascadeType.PERSIST

@Entity
class OrderHistory(name:String) {

    @Id
    @GeneratedValue
    var id: Long? = null
        protected set

    @Column
    var name: String = name

    @ManyToOne(cascade = [CascadeType.ALL])
    var order: Order? = null

    protected constructor() : this("")
}