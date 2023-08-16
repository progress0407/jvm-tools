package philo.simple.runner

import philo.simple.consumer.ConfigurableConsumer

fun main() {

    /** Simple Consumer */
//    val consumer = SimpleConsumer<String, String>()
//    consumer.doPolling()

    /** Multiple Consumer */
    val configurableConsumer = ConfigurableConsumer<String, String>(
            brokers = listOf("localhost:9092", "localhost:9093", "localhost:9094"),
            topicNames = listOf("multi-topic-test"),
            groupId = "multi-consumer-group-1"
    )
    configurableConsumer.doPolling()


    /** Custom Producer */
//    val customProducer = CustomProducer<String, String>()
//    customProducer.sendValue("testMessage2")
//    customProducer.sendValue(0, "some-key", "testMessage2")
//    customProducer.closeAndFlush()


    /** Simple Producer */
//    val simpleProducer = SimpleProducer<String, String>()
//    simpleProducer.sendValue("testMessage")
//    simpleProducer.closeAndFlush()
}