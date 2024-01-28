package io.philo.hexagonal.app.account.application.port.out

import io.philo.hexagonal.app.account.domain.BankAccount


interface LoadAccountPort {
    fun load(id: Long): BankAccount
}