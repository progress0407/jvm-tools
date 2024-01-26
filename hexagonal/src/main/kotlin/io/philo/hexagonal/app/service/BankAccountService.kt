package io.philo.hexagonal.app.service

import io.philo.hexagonal.app.BankAccount
import io.philo.hexagonal.app.port.`in`.DepositUseCase
import io.philo.hexagonal.app.port.`in`.WithdrawUseCase
import io.philo.hexagonal.app.port.out.LoadAccountPort
import io.philo.hexagonal.app.port.out.SaveAccountPort
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal


class BankAccountService : DepositUseCase, WithdrawUseCase {

    @Autowired
    private lateinit var loadAccountPort: LoadAccountPort

    @Autowired
    private lateinit var saveAccountPort: SaveAccountPort
    override fun deposit(id: Long?, amount: BigDecimal?) {
        val account: BankAccount = loadAccountPort.load(id)
        account.deposit(amount)
        saveAccountPort.save(account)
    }

    override fun withdraw(id: Long?, amount: BigDecimal?): Boolean {
        val account: BankAccount = loadAccountPort.load(id)
        val hasWithdrawn = account.withdraw(amount)
        if (hasWithdrawn) {
            saveAccountPort.save(account)
        }
        return hasWithdrawn
    }
}

