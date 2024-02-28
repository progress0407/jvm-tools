package io.philo.app

import jakarta.persistence.*

@Entity
class JpaSomeEntity(

    @Column
    private var name: String,

    @ElementCollection
    private var jsonStr: List<String> = emptyList()
) {
    constructor() : this("", emptyList()) {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
}