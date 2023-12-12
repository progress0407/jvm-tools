package io.philo.app

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed


@RedisHash(value = "refresh_token")
class RefreshToken(
    @Indexed
    private var token: String,

    @TimeToLive
    private var ttl: Long
) {

    @Id
    private val authId: String? = null

    fun update(token: String, ttl: Long): RefreshToken {
        this.token = token
        this.ttl = ttl
        return this
    }
}