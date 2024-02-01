package io.philo.springevent.app.post

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
//@Async
class PostDomainService(private val postDomainRepository: PostDomainRepository) {

    @Transactional
    fun execute(id: Long, percent: Int, ex: Boolean) {

        val postEntity = postDomainRepository.findById(id).orElseThrow { IllegalArgumentException("Not Found Entity.") }
        postEntity.updateValueByPercent(percent)
        if (ex) throwRuntimeException()
    }

    private fun throwRuntimeException() {

        throw RuntimeException("하지만 런타임 익셉션이 발생하면 어떨까? 익!셉!션!")
    }
}