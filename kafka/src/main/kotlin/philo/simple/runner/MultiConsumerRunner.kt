package philo.simple.runner

import philo.simple.consumer.ConfigurableConsumer
import philo.simple.consumer.RebalanceListener

fun main() {

    /** Multiple Consumer */
    val consumer = createConsumer()
    consumer.doPolling()
}

private fun createConsumer() = ConfigurableConsumer<String, String>(
        brokers = listOf("localhost:9092", "localhost:9093", "localhost:9094"),
        topicNames = listOf("multi-consuming-topic-test"),
        groupId = "multi-consumer-cluster",
        rebalanceListener = RebalanceListener()
)