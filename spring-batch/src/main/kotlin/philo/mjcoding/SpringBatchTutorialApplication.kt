package philo.mjcoding

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableBatchProcessing
@SpringBootApplication
class SpringBatchTutorialApplication

fun main() {
    SpringApplication.run(SpringBatchTutorialApplication::class.java)
}