package philo.jvmlabtools

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JvmLabToolsApplication

fun main(args: Array<String>) {
	runApplication<JvmLabToolsApplication>(*args)
}
