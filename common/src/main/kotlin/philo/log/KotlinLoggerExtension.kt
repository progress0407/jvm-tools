package philo.log

import mu.KLogger

class KotlinLoggerExtension

fun KLogger.infoGreen(message: () -> Any?) {
    this.info { "\u001B[32m${message.invoke()}\u001B[0m" }
}

fun KLogger.infoWarn(message: () -> Any?) {
    this.info { "\u001B[33m${message.invoke()}\u001B[0m" }
}