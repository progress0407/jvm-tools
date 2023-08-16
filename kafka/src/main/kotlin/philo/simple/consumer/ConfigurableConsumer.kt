package philo.simple.consumer

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import philo.log.infoGreen
import java.time.Duration
import java.util.*

class ConfigurableConsumer<K, V>(private val brokers: List<String>,
                                 private val topicNames: List<String>,
                                 private val groupId: String) {

    private val log: KLogger = KotlinLogging.logger {}

    private val consumer: KafkaConsumer<K, V> = initKafkaConsumer()

    private fun initKafkaConsumer(): KafkaConsumer<K, V> {
        val configs = initConfigs()
        log.infoGreen { "Kafka Consumer Construct !!" }
        return KafkaConsumer<K, V>(configs)
    }

    private fun initConfigs(): Properties {
        val configs = Properties()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = brokers
        configs[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        configs[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest" // from-beginning
        return configs
    }

    fun doPolling() {
        consumer.subscribe(topicNames)

        while (true) {
            val records = consumer.poll(Duration.ofMillis(500))
            for (record in records) {
                log.infoGreen { "polling record = ${record.value()}" }
            }
        }
    }
}