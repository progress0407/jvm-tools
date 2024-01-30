package io.philo.springevent.app.post

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostDomainService(private val postDomainRepository: PostDomainRepository) {

    @Transactional
    fun execute(id: Long, percent: Int) {

        val postEntity = postDomainRepository.findById(id).orElseThrow { IllegalArgumentException("Not Found Entity.") }
        postEntity.updateValueByPercent(percent)
    }
}