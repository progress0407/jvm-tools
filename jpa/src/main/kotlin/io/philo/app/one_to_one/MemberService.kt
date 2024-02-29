package io.philo.app.one_to_one

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class MemberService(private val memberRepository: MemberRepository) {
    @PostConstruct
    fun init() {
        val member = Member("member1")
        memberRepository.save(member)
        println("member = $member")
    }

    @Transactional
    fun create() {
        val member = Member(UUID.randomUUID().toString())
        memberRepository.save(member)
    }

    fun findAll() {
        memberRepository.findAll();
    }

    @Transactional
    fun delete(id: Long) {
        memberRepository.deleteById(id)
    }
}
