package io.philo.hexagonal.app

import java.math.BigDecimal


class BankAccount (val id: Long,
                   var balance: BigDecimal) {

    fun withdraw(amount: BigDecimal?): Boolean {
        if (balance.compareTo(amount) < 0) {
            return false
        }
        balance = balance.subtract(amount)
        return true
    }

    fun deposit(amount: BigDecimal?) {
        balance = balance.add(amount)
    }
}