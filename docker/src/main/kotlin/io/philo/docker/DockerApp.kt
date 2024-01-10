package io.philo.docker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerApp

fun main() {
    runApplication<DockerApp>()
}
