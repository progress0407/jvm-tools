package io.philo.spring_reactive.app

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux


interface CustomerRepository : ReactiveCrudRepository<Customer?, Long?> {
    @Query("SELECT * FROM customer WHERE last_name = :lastname")
    fun findByLastName(lastName: String?): Flux<Customer?>?
}