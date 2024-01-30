package io.philo.springevent.app.support

import io.philo.springevent.app.event.PreDomainEvent
import io.philo.springevent.app.post.PostDomainEntity
import io.philo.springevent.app.post.PostDomainRepository
import io.philo.springevent.app.pre.PreDomainEntity
import io.philo.springevent.app.pre.PreDomainRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class InitData(
    private val preEventRepository: PreDomainRepository,
    private val postEventRepository: PostDomainRepository
) {

    @PostConstruct
    fun initData() {

        val preDomain = PreDomainEntity(1L, 100)
        val postDomain = PostDomainEntity(1L, 1L, 100)

        preEventRepository.save(preDomain)
        postEventRepository.save(postDomain)
    }
}