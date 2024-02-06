package io.philo.springevent.app.post

import mu.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.log

@Service
//@Async
class PostDomainService(private val postDomainRepository: PostDomainRepository) {

    private val log = KotlinLogging.logger { }

    @Transactional
    fun execute(id: Long, percent: Int, ex: Boolean) {

        log.info { "수신처" }

        val postEntity = postDomainRepository.findById(id).orElseThrow { IllegalArgumentException("Not Found Entity.") }
        postEntity.updateValueByPercent(percent)
        if (ex) throwRuntimeException()
    }

    private fun throwRuntimeException() {

        throw RuntimeException("하지만 런타임 익셉션이 발생하면 어떨까? 익!셉!션!")
    }
}