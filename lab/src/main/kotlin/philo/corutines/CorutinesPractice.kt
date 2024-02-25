package philo.corutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class CorutinesPractice

fun main() = runBlocking {

    coco()
}

private suspend fun coco() = coroutineScope {
    val timeMillis = measureTimeMillis {
        println("run a async")
        val aDiffered = async { a() }
        println("run b async")
        val bDiffered = async { b() }
        println(aDiffered.await())
        println(bDiffered.await())
    }
    println("timeMillis = ${timeMillis}")
}

private suspend fun a():String {
    delay(2000)
    return "hi-a"
}

private suspend fun b():String {
    delay(2000)
    return "hi-2"
}
