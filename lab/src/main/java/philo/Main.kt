package philo

import mu.KotlinLogging

object Main {

    private val log = KotlinLogging.logger { }

    @JvmStatic
    fun main(args: Array<String>) {
        log.info { "Hello world!" }
    }
}