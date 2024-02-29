package io.philo.app.one_to_one

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(private val memberRepository: MemberRepository) {
    @PostConstruct
    fun init() {
        val member = Member("member1")
        memberRepository.save(member)
        println("member = $member")
    }
}
