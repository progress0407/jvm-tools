package io.philo.app.many_to_one

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}