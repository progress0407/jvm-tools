package philo.reflect

import philo.reflect.sample.KoKo
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

class KotlinReflectPracticeMain {
}

fun main() {

//    simpleCase()
//    classMetaData()
//    propNameIters()
//    callFunctionDynamicallyFunctions()
//    fieldGetterAndSetter()
//    kCallable()
//    constructClass()
    fieldAnnotationFinding()
}

private fun fieldAnnotationFinding() {

    println("============= Field Annotion =============")

/*
    for (property in Person::class.declaredMemberProperties) {
        val findAnnotation = property.findAnnotation<FiledAnnotation>()
        println("findAnnotation = $findAnnotation")
    }
*/

//    val findAnnotation = Person::age.findAnnotation<FieldAnnotation>()
//    println("findAnnotation = $findAnnotation")

//    val findAnnotation = KoKo::age.findAnnotation<FieldAnnotation>()
//    println("findAnnotation = ${findAnnotation}")

    KoKo::class.memberProperties.forEach {
        run {
            println("it.name = ${it.name}")
            println("it.annotations = ${it.annotations}")
            // If you want to access Java field directly, you can use:
            val field = it.javaField
            println("Field annotations: ${field?.annotations?.toList()}")
        }
    }
}

private fun kCallable() {
    println("============= KCallable =============")
    for (kCallable in Person::class.members) {
        println("kCallable.name = ${kCallable.name}")
        val findAnnotation: Example? = kCallable.findAnnotation<Example>()
        if (findAnnotation != null) {
            println("prop.findAnnotation<Example>() : ${kCallable.name}")
        }
    }
}

private fun fieldGetterAndSetter() {
    println("============= Field Getter And Setter =============")
    val newPerson2 = Person("Bob", 25)
    val ageProperty = Person::age
    println("Getter :  ${ageProperty.get(newPerson2)}")  // prints 25
    ageProperty.isAccessible = true
    ageProperty.setter.call(newPerson2, 250)
    println("After Setter Call, newPerson2 = $newPerson2")
}

private fun callFunctionDynamicallyFunctions() {
    println("============= Calling Functions Dynamically =============")
    val kFunction = Person::toString
    val newPerson = Person("Alice", 30)
    println(kFunction.call(newPerson))
}

private fun propNameIters() {
    println("============= Prop Names Iter =============")
    for (prop in Person::class.memberProperties) {
        println("prop.name : ${prop.name}")
        val findAnnotation: Example? = prop.findAnnotation<Example>()
        if (findAnnotation != null) {
            println("prop.findAnnotation<Example>() : ${prop.name}")
        }
    }
}

private fun classMetaData() {
    println("============= Class Meta Data =============")
    val personClass: KClass<Person> = Person::class
    println("personClass.simpleName = ${personClass.simpleName}")
    println("personClass.qualifiedName = ${personClass.qualifiedName}")
}

private fun simpleCase() {
    println("============= Simple Case =============")
    println("stringClass = ${String::class}")
}

private fun constructClass() {
    println("============= Construct Classes =============")
    println("NoArg::class.createInstance() = ${NoArg::class.createInstance()}")
    println("OptionalArgs::class.createInstance() = ${OptionalArgs::class.createInstance()}")
//    println("RequiredArgs::class.createInstance() = ${RequiredArgs::class.createInstance()}") // Occur Exception !!
    println("RequiredArgs::class.createInstance() = ${RequiredArgs::class.primaryConstructor!!.call("hello", "world")}")
}
