package philo.simple.consumer

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import philo.log.infoGreen
import java.time.Duration
import java.util.*

private val log: KLogger = KotlinLogging.logger {}

class SimpleConsumer {

    companion object {
        public val TOPIC_NAME = listOf("test-topic")
        public const val BOOTSTRAP_SERVERS = "localhost:9092"
        public const val GROUP_ID = "test-group2"
    }
}

fun main() {

    // Set the properties for the consumer
    val props = Properties()
    props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = SimpleConsumer.BOOTSTRAP_SERVERS
    props[ConsumerConfig.GROUP_ID_CONFIG] = SimpleConsumer.GROUP_ID
    props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
    props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
    props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest" // from-beginning

    // Create the consumer
    val consumer = KafkaConsumer<String, String>(props)

    // Subscribe to the 'test' topic
    consumer.subscribe(SimpleConsumer.TOPIC_NAME)

    // Continuously read from the 'test' topic
    while (true) {
        val records = consumer.poll(Duration.ofMillis(500))
        for (record in records) {
            log.infoGreen { "polling record = ${record.value()}" }
        }
    }
}