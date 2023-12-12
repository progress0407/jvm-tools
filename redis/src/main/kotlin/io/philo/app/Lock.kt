package io.philo.app

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit.SECONDS

@RedisHash(value = "lock")
class Lock(

    @Id
    private val paymentTxId: String,

//    @Indexed
//    private var token: String,

    @TimeToLive(unit = SECONDS)
    private var ttl: Int
) {

    fun update(ttl: Int): Lock {
        this.ttl = ttl
        return this
    }

    override fun toString(): String {
        return "Lock(paymentTxId='$paymentTxId', ttl=$ttl)"
    }
}