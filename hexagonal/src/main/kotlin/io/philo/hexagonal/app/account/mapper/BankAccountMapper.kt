package io.philo.hexagonal.app.account.mapper

import io.philo.hexagonal.app.account.domain.BankAccount
import io.philo.hexagonal.app.account.entity.BankAccountEntity
import org.springframework.stereotype.Component


@Component
class BankAccountMapper {
    fun toDomain(entity: BankAccountEntity): BankAccount {

        return BankAccount(entity.id!!, entity.balance)
    }

    fun toEntity(domain: BankAccount): BankAccountEntity {

        return BankAccountEntity(domain.balance)
    }
}