package philo.simple.consumer

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringSerializer
import philo.log.infoGreen
import java.time.Duration
import java.util.*

private val log: KLogger = KotlinLogging.logger {}

class SimpleConsumer<K, V> {

    companion object {
        const val BOOTSTRAP_SERVERS = "localhost:9092"
        val TOPIC_NAMES = listOf("test-topic")
        const val GROUP_ID = "test-group2"
    }

    private val consumer: KafkaConsumer<K, V> = initKafkaConsumer();

    private fun initKafkaConsumer(): KafkaConsumer<K, V> {
        val configs = initConfigs()
        log.infoGreen { "Kafka Consumer Construct !!" }
        return KafkaConsumer<K, V>(configs)
    }

    private fun initConfigs(): Properties {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVERS
        props[ConsumerConfig.GROUP_ID_CONFIG] = GROUP_ID
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest" // from-beginning
        return props
    }

    fun doPolling() {
        consumer.subscribe(TOPIC_NAMES)

        while (true) {
            val records = consumer.poll(Duration.ofMillis(500))
            for (record in records) {
                log.infoGreen { "polling record = ${record.value()}" }
            }
        }
    }
}

fun main() {
    val consumer = SimpleConsumer<String, String>()

    consumer.doPolling()
}