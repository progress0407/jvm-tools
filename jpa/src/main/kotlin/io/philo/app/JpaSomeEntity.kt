package io.philo.app

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.boot.autoconfigure.domain.EntityScan
import java.security.PrivateKey

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