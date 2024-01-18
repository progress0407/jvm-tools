package io.philo.docker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.philo.app", "io.philo.docker"])
class DockerApp

fun main() {
    runApplication<DockerApp>()
}
