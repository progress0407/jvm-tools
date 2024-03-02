package io.philo.app.many_to_many

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class ManyToManyInit(private val girlsAndBoysRepository: GirlsAndBoysRepository,
                     private val girlRepository: GirlRepository,
                     private val boyRepository: BoyRepository) {

    @PostConstruct
    fun init() {

        val 영희 = Girl("영희")
        val 민지 = Girl("민지")
        val 철수 = Boy("철수")
        val 광수 = Boy("광수")

        girlRepository.saveAll(listOf(영희, 민지))
        boyRepository.saveAll(listOf(철수, 광수))

        val 영희와철수 = GirlsAndBoys(영희, 철수)
        val 영희와광수 = GirlsAndBoys(영희, 광수)
        val 민지와철수 = GirlsAndBoys(민지, 철수)
        val 민지와광수 = GirlsAndBoys(민지, 광수)
        girlsAndBoysRepository.saveAll(listOf(영희와철수, 영희와광수, 민지와철수, 민지와광수))

        println("init")
    }
}