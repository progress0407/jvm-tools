package io.philo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collation = "chat")
class Chat {

    @Id
    private var id: String? = null
    private var message: String? = null
    private var sender: String? = null
    private var receiver: String? = null
    private var createdAt = LocalDateTime.now()
}