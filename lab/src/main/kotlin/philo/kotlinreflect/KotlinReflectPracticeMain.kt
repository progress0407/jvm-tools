package philo.kotlinreflect

import kotlin.reflect.KClass

class KotlinReflectPracticeMain {
}

fun main() {
    println("3 = ${3}")
    val stringClass = String::class
    println("stringClass = ${stringClass}")

    val person = Person("Alice", 29)
    val memberProperty = Person::age
    println(memberProperty.get(person)) // 29

    val personClass: KClass<Person> = Person::class
    println("personClass.simpleName = ${personClass.simpleName}")
    println("personClass.qualifiedName = ${personClass.qualifiedName}")
}

class Person(val name: String, val age: Int)
class NoArg
class OptionalArgs(val arg: String = "default")
class RequiredArgs(val arg1: String, val arg2: String) {
    constructor(arg1: String) : this(arg1, "default")
}