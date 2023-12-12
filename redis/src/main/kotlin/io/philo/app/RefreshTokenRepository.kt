package io.philo.app

import org.springframework.data.repository.CrudRepository


interface RefreshTokenRepository : CrudRepository<RefreshToken?, String?> {
    fun findByToken(token: String?): RefreshToken?
    fun findByAuthId(authId: String?): RefreshToken
}