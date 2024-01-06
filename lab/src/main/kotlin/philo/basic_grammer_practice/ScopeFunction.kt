package philo.basic_grammer_practice

class ScopeFunction

fun main() {

    val person = Person("philz", 10)

//    `let`(person)
//    `apply`(person)
    `selfMadeScopeFunc`(person)
}

private fun `selfMadeScopeFunc`(person: Person) {
    val result = person.selfMadeScopeFunc {
        println("name = ${name}")
    }
    println("result = ${result}")
}

private fun `apply`(person: Person) {
    val obj = person.apply {
        println("name= $name")
        name
    }
    println("obj = ${obj}")
}

private fun `let`(person: Person) {
    val age = person.let {
        println(it)
        it.age
    }
    println("age = ${age}")
}

private data class Person(var name: String = "", var age: Int = 0)

private inline fun <T> T.selfMadeScopeFunc(block: T.() -> Unit): T {

    block()

    return this
}