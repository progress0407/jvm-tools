package philo.kotlinreflect

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible

class KotlinReflectPracticeMain {
}

fun main() {
    println("============= Simple Case =============")
    println("stringClass = ${String::class}")


    println("============= Class Meta Data =============")
    val personClass: KClass<Person> = Person::class
    println("personClass.simpleName = ${personClass.simpleName}")
    println("personClass.qualifiedName = ${personClass.qualifiedName}")


    println("============= Prop Names Iter =============")
    for (prop in personClass.memberProperties) {
        println("prop.name : ${prop.name}")
        val findAnnotation: Example? = prop.findAnnotation<Example>()
        if (findAnnotation != null) {
            println("prop.findAnnotation<Example>() : ${prop.name}")
        }
    }


    println("============= Calling Functions Dynamically =============")
    val kFunction = Person::toString
    val newPerson = Person("Alice", 30)
    println(kFunction.call(newPerson))


    println("============= Getter And Setter =============")
    val newPerson2 = Person("Bob", 25)
    val ageProperty = Person::age
    println("Getter :  ${ageProperty.get(newPerson2)}")  // prints 25
    ageProperty.isAccessible = true
    ageProperty.setter.call(newPerson2, 250)
    println("After Setter Call, newPerson2 = ${newPerson2}")


    println("============= KCallable =============")
    for (kCallable in personClass.members) {
        println("kCallable.name = ${kCallable.name}")
        val findAnnotation: Example? = kCallable.findAnnotation<Example>()
        if (findAnnotation != null) {
            println("prop.findAnnotation<Example>() : ${kCallable.name}")
        }
    }

    println("Construct Classes")
    println("NoArg::class.createInstance() = ${NoArg::class.createInstance()}")
    println("OptionalArgs::class.createInstance() = ${OptionalArgs::class.createInstance()}")
//    println("RequiredArgs::class.createInstance() = ${RequiredArgs::class.createInstance()}") // Occur Exception !!
    println("RequiredArgs::class.createInstance() = ${RequiredArgs::class.primaryConstructor!!.call("hello", "world")}")
}

class Person(val name: String, var age: Int) {

    @Example
    fun doSomething() {}

    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}
class NoArg
class OptionalArgs(val arg: String = "default")
class RequiredArgs(val arg1: String, val arg2: String) {
    constructor(arg1: String) : this(arg1, "default")
}

annotation class Example
