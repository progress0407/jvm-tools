import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.*

class SimpleConsumer {

    fun main(args: Array<String>) {
        // Set the properties for the consumer
        val props = Properties()
        props["bootstrap.servers"] = "localhost:9092"
        props["group.id"] = "test"
        props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"

        // Create the consumer
        val consumer = KafkaConsumer<String, String>(props)

        // Subscribe to the 'test' topic
        consumer.subscribe(mutableListOf("test"))

        // Continuously read from the 'test' topic
        while (true) {
            val records = consumer.poll(500)
            for (record in records) {
                System.out.printf(record.value())
            }
        }
    }
}