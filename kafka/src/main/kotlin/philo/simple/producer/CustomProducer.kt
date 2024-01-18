package philo.simple.producer

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import io.philo.log.infoGreen
import java.util.*

private val log: KLogger = KotlinLogging.logger {}

class CustomProducer<K, V> {

    companion object {
        const val BOOTSTRAP_SERVERS = "localhost:9092"
        const val TOPIC_NAME = "test-topic"
    }

    private val log: KLogger = KotlinLogging.logger {}

    private val kafkaProducer: KafkaProducer<K, V> = initKafkaProducer()

    private fun initKafkaProducer(): KafkaProducer<K, V> {
        val configs = initConfigs()
        log.infoGreen { "Kafka Producer Construct !!" }
        return KafkaProducer<K, V>(configs)
    }

    private fun initConfigs(): Properties {
        val configs = Properties()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVERS
        configs[ProducerConfig.PARTITIONER_CLASS_CONFIG] = CustomPartitioner::class.java
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        return configs
    }

    fun sendValue(value: V) {
        val record = createRecord(value)
        kafkaProducer.send(record)
        log.infoGreen { "send record :  $record" }
    }

    fun sendValue(partitionNumber: Int, key: K, value: V) {
        val record = createRecord(partitionNumber, key, value)
        kafkaProducer.send(record)
        log.infoGreen { "send record :  $record" }
    }

    fun closeAndFlush() {
        kafkaProducer.flush()
        kafkaProducer.close()
    }

    private fun createRecord(value: V) = ProducerRecord<K, V>(TOPIC_NAME, value)

    private fun createRecord(partitionNumber: Int, key: K, value: V) = ProducerRecord<K, V>(TOPIC_NAME, partitionNumber, key, value)
}
