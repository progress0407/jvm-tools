package philo.reflect

import philo.reflect.sample.SomeAnnotation

class KReflectSample

class Person(val name: String,
             @SomeAnnotation var age: Int) {

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
