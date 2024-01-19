package io.philo.k8s.app

import io.philo.app.entity.PersonEntity
import io.philo.app.repository.PersonRepository
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class K8sController(
    private val repository: PersonRepository,
    @Value("\${user.server.version}") private val version: String,
) {

    private val randomStr = UUID.randomUUID().toString()

    @PostConstruct
    fun init() {
        println("### current version is $version !")
    }

    @GetMapping("/hi")
    fun hi() = "Hi, there !!"

    @PostMapping("/person")
    fun add() = repository.save(PersonEntity())

    @GetMapping("/person")
    fun list() = repository.findAll()

    @GetMapping("/version")
    fun version() = "current application version is $version, server uuid: $randomStr\n"

    @GetMapping("/cookie-from-client")
    fun cookieFromClient(request: HttpServletRequest): String {

        val cookies = request.cookies
            ?: return "nothing any cookies!"

        cookies.forEach { println(it) }

        val findCookie = cookies
            .find { it.name == "cook-cook" }

        return if (findCookie == null)
            "nothing!"
        else
            "find it ! ${findCookie.value}"
    }

    @GetMapping("/cookie-from-server")
    fun cookieFromServer(response: HttpServletResponse): String {

        val cookie = Cookie("some-info", "this_is_only_your_cookie!")
        cookie.path="/"
        cookie.maxAge = 3600 // 1 hour
        response.addCookie(cookie)

        return "cookie is set, check!"
    }

}