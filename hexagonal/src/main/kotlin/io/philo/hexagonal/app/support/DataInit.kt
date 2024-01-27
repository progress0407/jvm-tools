package io.philo.hexagonal.app.support

import io.philo.hexagonal.app.BankAccount
import io.philo.hexagonal.app.entity.BankAccountEntity
import io.philo.hexagonal.app.repository.BankAccountSpringDataRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataInit(private val repository: BankAccountSpringDataRepository) {

    @PostConstruct
    fun initData() {
        repository.save(BankAccountEntity())
    }
}