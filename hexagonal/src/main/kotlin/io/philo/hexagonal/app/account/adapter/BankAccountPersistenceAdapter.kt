package io.philo.hexagonal.app.account.adapter

import io.philo.hexagonal.app.account.domain.BankAccount
import io.philo.hexagonal.app.account.entity.BankAccountEntity
import io.philo.hexagonal.app.account.mapper.BankAccountMapper
import io.philo.hexagonal.app.account.application.port.out.LoadAccountPort
import io.philo.hexagonal.app.account.application.port.out.SaveAccountPort
import io.philo.hexagonal.app.account.adapter.out.BankAccountSpringDataRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Repository
class BankAccountPersistenceAdapter(
    private val bankAccountMapper: BankAccountMapper,
    private val repository: BankAccountSpringDataRepository,
) : LoadAccountPort, SaveAccountPort {

    override fun load(id: Long): BankAccount {

        val entity = repository.findById(id).orElseThrow { NoSuchElementException() }

        return bankAccountMapper.toDomain(entity)
    }

    override fun save(bankAccount: BankAccount) {

        val entity: BankAccountEntity = bankAccountMapper.toEntity(bankAccount)

        repository.save(entity)
    }
}