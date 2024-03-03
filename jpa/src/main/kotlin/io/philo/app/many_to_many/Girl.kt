package io.philo.app.many_to_many

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Girl(name: String) {

    @Id @GeneratedValue
    var id: Long? = null
        protected set

    @Column
    var name: String = name
        protected set

    protected constructor() : this("")
}