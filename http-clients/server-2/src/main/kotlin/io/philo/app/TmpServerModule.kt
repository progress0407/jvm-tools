package io.philo.app

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * todo! 리펙터링 대상, 별도 모듈로 분리할 것
 */
@RestController
class TmpServerModule {

    @RequestMapping("/listen")
    fun listen(): String {
        return "ok"
    }
}