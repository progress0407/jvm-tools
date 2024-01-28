package io.philo.hexagonal.app.account.application

import io.philo.hexagonal.app.account.domain.BankAccount
import io.philo.hexagonal.app.account.application.port.`in`.DepositUseCase
import io.philo.hexagonal.app.account.application.port.`in`.WithdrawUseCase
import io.philo.hexagonal.app.account.application.port.out.LoadAccountPort
import io.philo.hexagonal.app.account.application.port.out.SaveAccountPort
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class BankAccountService(
    private val loadAccountPort: LoadAccountPort,
    private val saveAccountPort: SaveAccountPort
) : DepositUseCase, WithdrawUseCase {

    override fun deposit(id: Long, amount: BigDecimal) {

        val account: BankAccount = loadAccountPort.load(id)
        account.deposit(amount)
        saveAccountPort.save(account)
    }

    override fun withdraw(id: Long, amount: BigDecimal): Boolean {

        val account: BankAccount = loadAccountPort.load(id)
        val hasWithdrawn = account.withdraw(amount)
        if (hasWithdrawn) {
            saveAccountPort.save(account)
        }
        return hasWithdrawn
    }
}

