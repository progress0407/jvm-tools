package io.philo.app.tx_with_query

import io.philo.entity.PersonEntity
import io.philo.repository.PersonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TxWithQueryService(private val personRepository: PersonRepository) {

    @Transactional
    fun tx_test_1() {

        val personEntity = PersonEntity("John Doe")
        personRepository.save(personEntity)

        internal_tx_test_1(personEntity.id!!)
    }

    @Transactional
    fun internal_tx_test_1(id: Long) {

        val foundMember = personRepository.findById(id).orElse(null)
        foundMember.changeName("Updated Jane Doe")
    }
}
