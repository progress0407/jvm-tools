package philo.kotlin_dsl

import kotlin.properties.Delegates

class DockerComposeDsl

fun main() {
    val yml = dockerCompose {
        version { 3 }
    }

    println("yml = ${yml}")
}

private fun dockerCompose(init: DockerCompose.() -> Unit): DockerCompose {

    val dockerCompose = DockerCompose()
    dockerCompose.init()
    return dockerCompose
}

private class DockerCompose {

    private var version: Int by Delegates.notNull()

    fun version(init: () -> Int) {
        version = init()
    }
}
