package philo.generic

class GenericMain

fun main() {

    val a: Int = 1
    val b: Float = 2F

    println(someFunc(a, b))

    val bs: List<Float> = listOf(1F, 1F)

    println(someFunc(a, bs))
}

private fun <T: Number> someFunc(a: T, b: T): Double {
    return a.toDouble() + b.toDouble()
}

private fun <T: Number> someFunc(a: T, bs: List<T>): Double {
    return a.toDouble() + bs.sumOf { it.toDouble() }
}