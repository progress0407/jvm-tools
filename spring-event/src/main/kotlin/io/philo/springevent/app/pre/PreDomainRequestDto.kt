package io.philo.springevent.app.pre

data class PreDomainRequestDto(
    val id:Long,
    val percent:Int,
    val ex: Boolean // 이벤트 리스너에서 예외 발생 여부
)
