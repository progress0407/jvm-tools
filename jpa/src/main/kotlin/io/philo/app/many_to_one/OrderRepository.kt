package io.philo.app.many_to_one

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderRepository : JpaRepository<Order, Long> {

    @Query("select o from Order o where o.id = :id")
    fun findSomething(@Param("id") id: Long): List<Order>
}