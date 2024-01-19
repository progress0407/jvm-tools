package io.philo.app.reactive

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ReactiveOps {
}

fun main() {

    val dataXs: Flux<DataX> = Flux.just(DataX(1, 100), DataX(2, 200))
    val dataYs: Flux<DataY> = Flux.just(DataY(1, 500))

//    ex1(dataXs, dataYs)
    ex3(dataXs, dataYs)
}

private fun ex1(
    dataXs: Flux<DataX>,
    dataYs: Flux<DataY>,
) {
    val dataXY = Flux.zip(dataXs, dataYs) { itemX, itemY ->
        DataXY(itemX.a, "${itemX?.x ?: "none"}:${itemY?.y ?: "none"}")
    }.doOnNext { println("log = $it") }

    dataXY.subscribe { println(it) }
}

private fun ex2(
    dataXs: Flux<DataX>,
    dataYs: Flux<DataY>,
) {
    val dataXY = Flux.zip(dataXs, dataYs) { itemX: DataX, itemY: DataY ->
        if(itemY == null)
        DataXY(itemX.a, "${itemX?.x ?: "none"}:${itemY?.y ?: "none"}")
    }

    dataXY.subscribe { println(it) }
}

private fun ex3(
    dataXs: Flux<DataX>,
    dataYs: Flux<DataY>,
) {

    val ys = dataYs.collectList()

    val res: Flux<Pair<DataX, Int>> = dataXs.flatMap { x: DataX ->
        val yCnt = ys.map { it: MutableList<DataY> ->
            it.filter { it.a == x.a }.size
        }.block()
        val pair: Pair<DataX, Int> = Pair(x, yCnt!!)
        Mono.just(Pair(x, yCnt))
    }

    res.subscribe { println(it) }
}


private data class DataX(val a: Int, val x: Int)
private data class DataY(val a: Int, val y: Int)
private data class DataXY(val a: Int, val xy: String)
