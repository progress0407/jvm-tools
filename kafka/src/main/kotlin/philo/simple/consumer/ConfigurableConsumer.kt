package philo.simple.consumer

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import philo.log.infoGreen
import java.time.Duration
import java.util.*

class ConfigurableConsumer<K, V>(private val brokers: List<String> = listOf("localhost:9092"),
                                 private val topicNames: List<String>,
                                 private val groupId: String,
                                 private val rebalanceListener: ConsumerRebalanceListener? = null) {

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
        configs[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false
        configs[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest" // from-beginning
        return configs
    }

    fun doPolling() {
        consumer.subscribeOnConditional()

        while (true) {
            val records = consumer.poll(Duration.ofMillis(1000))
            log.infoGreen { "poll records" }
            for (record in records) {
                log.infoGreen { "polling record = ${record.value()}" }
            }
            consumer.commitAsyncWithCallback()
        }
    }

    private fun KafkaConsumer<K, V>.subscribeOnConditional() {
        if(rebalanceListener == null)
            consumer.subscribe(topicNames)
        else
            consumer.subscribe(topicNames, rebalanceListener)
    }

    private fun KafkaConsumer<K, V>.commitAsyncWithCallback() {
        consumer.commitAsync { offsets, exception ->
            if (exception == null) {
                println("Commit succeeded for offsets: $offsets")
            } else {
                println("Commit failed for offsets: $offsets")
                exception.printStackTrace()
            }
        }
    }
}