package io.philo

import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ChatRepository: ReactiveMongoRepository<Chat, String> {

    @Query("{sender: ?0, receiver: ?1}")
    fun findBySender(sender: String, receiver: String): Flux<Chat>
}