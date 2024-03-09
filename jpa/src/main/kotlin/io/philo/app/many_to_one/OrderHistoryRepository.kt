package io.philo.app.many_to_one

import org.springframework.data.jpa.repository.JpaRepository

interface OrderHistoryRepository: JpaRepository<OrderHistory, Long> {
}