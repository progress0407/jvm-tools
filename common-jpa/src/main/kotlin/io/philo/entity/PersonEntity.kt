package io.philo.entity

import jakarta.persistence.*
import java.util.*

@Entity
class PersonEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",
) {

    constructor(name: String) : this(id = null, name = name)

    constructor() : this(name = UUID.randomUUID().toString())

    override fun toString(): String {
        return "PersonEntity(id=$id, name='$name')"
    }
}