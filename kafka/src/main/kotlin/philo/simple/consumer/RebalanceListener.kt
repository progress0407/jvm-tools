package philo.simple.consumer

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.common.TopicPartition
import philo.log.infoGreen
import philo.log.infoWarn


class RebalanceListener : ConsumerRebalanceListener {

    private val log: KLogger = KotlinLogging.logger {}

    override fun onPartitionsRevoked(partitions: Collection<TopicPartition>) {
        log.infoWarn { "Rebalancing Revoke ! Before Partitions :: \n${partitions.info()}" }
    }

    override fun onPartitionsAssigned(partitions: Collection<TopicPartition>) {
        log.infoGreen { "Partitions are reassigned ! After Partitions:: \n${partitions.info()}" }
    }

    private fun Collection<TopicPartition>.info() = this.joinToString(separator = "\n")
}