package io.philo.docker.app

import jakarta.persistence.*
import java.util.*

@Entity
class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false)
    var name = ""

    constructor() {
        this.name = UUID.randomUUID().toString()
    }
}