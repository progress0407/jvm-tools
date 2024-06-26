package io.philo.entity

import jakarta.persistence.*
import java.util.*

@Entity
class PersonEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",
) {

//    constructor(name: String) : this(id = Random().nextLong(1, 10_000), name = name)
    constructor(name: String) : this(id = null, name = name)

    constructor() : this(name = UUID.randomUUID().toString())

    fun changeName(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return "PersonEntity(id=$id, name='$name')"
    }
}