package philo.simple.runner

import philo.simple.consumer.ConfigurableConsumer

fun main() {

    /** Multiple Consumer */
    val configurableConsumer = ConfigurableConsumer<String, String>(
            brokers = listOf("localhost:9092", "localhost:9093", "localhost:9094"),
            topicNames = listOf("multi-topic-test"),
            groupId = "multi-consumer-group-1"
    )
    configurableConsumer.doPolling()
}