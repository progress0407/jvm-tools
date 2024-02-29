package io.philo.app.one_to_one

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/jpa-some/members")
@RestController
class MemberController(private val memberService: MemberService) {

    @PostMapping
    fun create() {

    }
}