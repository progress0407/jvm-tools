package io.philo.app.presentation

import io.philo.app.entity.PersonEntity
import io.philo.app.repository.PersonRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController(private val personRepository: PersonRepository) {

    @RequestMapping("/test-save")
    @Transactional
    fun testSave() {

        personRepository.save(PersonEntity(1, "foo_11"))

        personRepository.findAll()
    }

    @RequestMapping("/test-save-2")
    @Transactional
    fun testSave2() {

        personRepository.save(PersonEntity(1, "boo_22"))

        personRepository.findAll()
    }

    @RequestMapping("/test-get")
    @Transactional
    fun testGet() {

        personRepository.findAll()
    }
}
