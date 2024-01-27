package io.philo.hexagonal.app.repository

import io.philo.hexagonal.app.entity.BankAccountEntity

import org.springframework.data.jpa.repository.JpaRepository


interface BankAccountSpringDataRepository : JpaRepository<BankAccountEntity, Long>