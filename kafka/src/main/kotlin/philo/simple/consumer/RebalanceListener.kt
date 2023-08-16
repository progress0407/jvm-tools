package philo.simple.consumer

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.common.TopicPartition


class RebalanceListener : ConsumerRebalanceListener {

    private val log: KLogger = KotlinLogging.logger {}

    override fun onPartitionsRevoked(partitions: Collection<TopicPartition>) {
        log.warn("Rebalancing Revoke ! $partitions")
    }

    override fun onPartitionsAssigned(partitions: Collection<TopicPartition>) {
        log.warn("Partitions are reassigned $partitions")
    }
}