package io.philo.hexagonal.app.port.out

import io.philo.hexagonal.app.BankAccount


interface LoadAccountPort {
    fun load(id: Long): BankAccount
}