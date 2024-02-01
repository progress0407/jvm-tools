package io.philo

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    companion object {
        private const val EVENT_PREFIX = "registry.updated"
        const val QUEUE_NAME = "$EVENT_PREFIX.queue"
        const val EXCHANGE_NAME = "$EVENT_PREFIX.exchange"
        const val ROUTING_KEY = "$EVENT_PREFIX.routing.#"
    }

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter = Jackson2JsonMessageConverter()

    @Bean
    fun queue() = Queue(QUEUE_NAME)

    @Bean
    fun exchange() = DirectExchange(EXCHANGE_NAME)

    @Bean
    fun binding(
        queue: Queue,
        exchange: DirectExchange
    ): Binding =
        BindingBuilder
            .bind(queue)
            .to(exchange)
            .with(ROUTING_KEY)

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: Jackson2JsonMessageConverter
    ): RabbitTemplate {

        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter

        return rabbitTemplate
    }
}