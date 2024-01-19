package io.philo.k8s

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.philo.app", "io.philo.docker"])
class K8sApp

fun main() {
    runApplication<K8sApp>()
}
