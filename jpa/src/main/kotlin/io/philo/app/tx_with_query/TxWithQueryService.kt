package io.philo.app.tx_with_query

import io.philo.entity.PersonEntity
import io.philo.repository.PersonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TxWithQueryService(private val personRepository: PersonRepository) {

    @Transactional
    fun method1() {

        val personEntity = PersonEntity("John Doe")
        personRepository.save(personEntity)

        method2(personEntity.id!!)
    }

    @Transactional
    fun method2(id: Long) {

        val foundMember = personRepository.findById(id).orElse(null)
//        throw RuntimeException("Rollback")
        foundMember.changeName("Updated Jane Doe")
    }
}
