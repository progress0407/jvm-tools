package philo.simple.runner

import philo.simple.consumer.ConfigurableProducer


fun main() {

    val producer = ConfigurableProducer<String, String>(
            brokers = listOf("localhost:9092", "localhost:9093", "localhost:9094"),
            topicName = "multi-topic-test"
    )

    for (i in 1..1000) {
        producer.sendValue("test message, cnt : $i")
        Thread.sleep(500L)
    }

    producer.closeAndFlush()
}
