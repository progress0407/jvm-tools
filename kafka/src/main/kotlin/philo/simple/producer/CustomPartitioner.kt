package philo.simple.producer

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster
import org.apache.kafka.common.InvalidRecordException
import org.apache.kafka.common.utils.Utils

class CustomPartitioner: Partitioner {

    override fun configure(configs: MutableMap<String, *>?) {
        TODO("Not yet implemented")
    }

    override fun partition(topic: String?, key: Any?, keyBytes: ByteArray?, value: Any?, valueBytes: ByteArray?, cluster: Cluster): Int {
        if(keyBytes == null) {
            throw InvalidRecordException("Need Message Key")
        }

        if (key as String == "Pangyo") {
            return 0
        }

        val partitions = cluster.partitionsForTopic(topic)
        val partitionsNumber = partitions.size

        return Utils.toPositive(Utils.murmur2(keyBytes)) % partitionsNumber
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}
