package philo.simple.runner

import philo.simple.producer.SimpleProducer


fun main() {

    val simpleProducer = SimpleProducer<String, String>()

    var i: Int = 1;
    while (true) {
        simpleProducer.sendValue("test message, cnt : ${i++}")
    }

    simpleProducer.closeAndFlush()
}
