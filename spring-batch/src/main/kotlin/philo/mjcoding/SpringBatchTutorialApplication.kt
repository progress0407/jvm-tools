package philo.mjcoding

import mu.KotlinLogging
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@EnableBatchProcessing
@SpringBootApplication
@EnableScheduling
class SpringBatchTutorialApplication {

    private val log = KotlinLogging.logger { }

    @Scheduled(cron = "*/5 * * * * ?")
    fun run() {
        log.info { "run " }
    }
}

fun main() {
    SpringApplication.run(SpringBatchTutorialApplication::class.java)
}