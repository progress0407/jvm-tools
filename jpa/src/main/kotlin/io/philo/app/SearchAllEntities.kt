package io.philo.app

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Component

@Component
class SearchAllEntities {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @PostConstruct
    fun init() {
        val entities = entityManager.metamodel.entities
        println("entities = ${entities}")
    }
}