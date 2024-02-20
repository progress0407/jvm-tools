package io.philo.app

import io.philo.entity.PersonEntity
import io.philo.repository.PersonRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/persons")
class PersonController(private val repository: PersonJdslRepository) {

    @GetMapping("/jdsl-get/{id}")
    fun testGet(@PathVariable id: Long): MutableList<PersonEntity> {

        val results = repository.findBy(id)

        return results
    }
}
