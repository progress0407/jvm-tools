package io.philo.docker.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DockerController(private val repository: PersonRepository) {


    @GetMapping("/hi")
    fun hi() = "Hi, there !!"

    @PostMapping("/person")
    fun add() = repository.save(Person())

    @GetMapping("/person")
    fun list() = repository.findAll()
}