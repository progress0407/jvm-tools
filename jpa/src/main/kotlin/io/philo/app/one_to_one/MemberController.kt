package io.philo.app.one_to_one

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RequestMapping("/jpa-some/members")
@RestController
class MemberController(private val memberService: MemberService) {

    @PostMapping
    fun create() {
        memberService.create()
    }

    @GetMapping()
    fun findAll() {
        memberService.findAll()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        memberService.delete(id)
    }
}
