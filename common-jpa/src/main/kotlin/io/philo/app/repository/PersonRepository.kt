package io.philo.app.repository

import io.philo.app.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<PersonEntity, Long> {
}