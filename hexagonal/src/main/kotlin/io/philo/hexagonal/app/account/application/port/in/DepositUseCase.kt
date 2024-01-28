package io.philo.hexagonal.app.account.application.port.`in`

import java.math.BigDecimal


interface DepositUseCase {
    fun deposit(id: Long, amount: BigDecimal)
}