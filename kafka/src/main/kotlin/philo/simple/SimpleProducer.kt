package philo.simple

import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import philo.log.infoGreen
import java.util.*

private val log: KLogger = KotlinLogging.logger {}

class SimpleProducer {

    private val log: KLogger = KotlinLogging.logger {}

    init {
        log.infoGreen { "Hello World" }
    }

    companion object {
        public const val TOPIC_NAME = "test-topic"
        public const val BOOTSTRAP_SERVERS = "localhost:9092"
    }
}

fun main() {
    val simpleProducer = SimpleProducer()

    val configs = Properties()
    configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = SimpleProducer.BOOTSTRAP_SERVERS
    configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
    configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name

    val kafkaProducer = KafkaProducer<String, String>(configs)

    val messageValue = "testMessage"

    val record = ProducerRecord<String, String>(SimpleProducer.TOPIC_NAME, messageValue)
    kafkaProducer.send(record)
    log.infoGreen { "send record :  ${record}" }
    kafkaProducer.flush()
    kafkaProducer.close()
}