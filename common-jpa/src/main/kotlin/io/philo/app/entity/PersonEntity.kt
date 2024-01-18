package io.philo.app.entity

import jakarta.persistence.*
import java.util.*

@Entity
class PersonEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false)
    var name = ""

    constructor() {
        this.name = UUID.randomUUID().toString()
    }
}