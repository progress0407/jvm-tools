package io.philo.repository

import io.philo.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<PersonEntity, Long> {
}