package io.philo.docker.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DockerController {

    @GetMapping("/hi")
    fun hi() = "Hi, there !!"
}