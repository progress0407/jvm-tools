package io.philo.docker.app

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DockerController(
    private val repository: PersonRepository,
    @Value("\${user.server.version}") private val version: String
) {

    private val randomStr = UUID.randomUUID().toString()


    @GetMapping("/hi")
    fun hi() = "Hi, there !!"

    @PostMapping("/person")
    fun add() = repository.save(Person())

    @GetMapping("/person")
    fun list() = repository.findAll()

    @GetMapping("/version")
    fun version() = "current application version is $version, server uuid: $randomStr\n"
}