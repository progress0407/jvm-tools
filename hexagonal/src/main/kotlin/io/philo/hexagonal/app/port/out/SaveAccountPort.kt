package io.philo.hexagonal.app.port.out

import io.philo.hexagonal.app.BankAccount


interface SaveAccountPort {
    fun save(bankAccount: BankAccount?)
}