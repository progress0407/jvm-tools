package io.philo.springevent.app.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostDomainRepository: JpaRepository<PostDomainEntity, Long> {
}