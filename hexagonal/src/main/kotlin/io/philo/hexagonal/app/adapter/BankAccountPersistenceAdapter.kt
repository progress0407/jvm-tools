package io.philo.hexagonal.app.adapter

import io.philo.hexagonal.app.BankAccount
import io.philo.hexagonal.app.entity.BankAccountEntity
import io.philo.hexagonal.app.mapper.BankAccountMapper
import io.philo.hexagonal.app.port.out.LoadAccountPort
import io.philo.hexagonal.app.port.out.SaveAccountPort
import io.philo.hexagonal.app.repository.BankAccountSpringDataRepository
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