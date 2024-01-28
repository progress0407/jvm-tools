package io.philo.hexagonal.app.account.application.port.out

import io.philo.hexagonal.app.account.domain.BankAccount


interface SaveAccountPort {
    fun save(bankAccount: BankAccount)
}