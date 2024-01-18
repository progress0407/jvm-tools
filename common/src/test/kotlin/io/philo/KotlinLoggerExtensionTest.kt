package io.philo

import mu.KotlinLogging
import io.philo.log.infoGreen

class KotlinLoggerExtensionTest

val log = KotlinLogging.logger {  }

fun main() {
    io.philo.log.infoGreen { "hello this message color is greent" }
}