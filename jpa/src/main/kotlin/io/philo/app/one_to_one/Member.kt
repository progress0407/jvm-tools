package io.philo.app.one_to_one

import com.fasterxml.jackson.databind.node.LongNode
import jakarta.persistence.*

@Entity
class Member(username: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    var id: Long? = null
        protected set

    var username: String = username
        protected set

    @OneToOne(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    var locker: Locker = Locker("default", this)

    protected constructor() : this("")

    fun changeName(name: String) {
        this.username = name
    }
}
