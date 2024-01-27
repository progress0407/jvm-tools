package io.philo.hexagonal.app.controller

import io.philo.hexagonal.app.port.`in`.DepositUseCase
import io.philo.hexagonal.app.port.`in`.WithdrawUseCase
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal


@RequestMapping("/account/{id}")
@RestController
class BankAccountController(
    private val depositUseCase: DepositUseCase,
    private val withdrawUseCase: WithdrawUseCase,
) {
    @PostMapping("/deposit/{amount}")
    fun deposit(@PathVariable id: Long, @PathVariable amount: BigDecimal) {

        depositUseCase.deposit(id, amount)
    }

    @PostMapping("/withdraw/{amount}")
    fun withdraw(@PathVariable id: Long, @PathVariable amount: BigDecimal) {

        withdrawUseCase.withdraw(id, amount)
    }
}