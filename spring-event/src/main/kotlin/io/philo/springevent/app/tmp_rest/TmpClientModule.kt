package io.philo.springevent.app.tmp_rest

import mu.KotlinLogging
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpRequest
import org.springframework.http.client.*
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.function.Supplier
import java.util.stream.Collectors


/**
 * todo! 리펙터링 대상, 별도 모듈로 분리할 것
 */
@Component
class TmpClientModule {

    private val log = KotlinLogging.logger { }

    private lateinit var restTemplate: RestTemplate

    /*
        init {

            val timeout = 1_200_000 // 20 minutes in milliseconds
            val factory = SimpleClientHttpRequestFactory()
            factory.setReadTimeout(timeout)
            factory.setConnectTimeout(timeout)
            restTemplate = RestTemplate(factory)
        }
    */

    init {

        val timeOut = Duration.ofSeconds(1_200)
        restTemplate = RestTemplateBuilder()
            .setConnectTimeout(timeOut)
            .setReadTimeout(timeOut)
            .additionalInterceptors(LoggingInterceptor())
            .requestFactory(Supplier { BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()) })
            .build()
    }

    @Scheduled(fixedDelay = 1_000)
    fun caller() {

        val result = restTemplate.getForEntity("http://localhost:8081/listen", String::class.java);

        log.info { "something doing -- status: ${result.statusCode}, return value: ${result.body}" }
    }
}

internal class LoggingInterceptor : ClientHttpRequestInterceptor {

    private val log = KotlinLogging.logger { }

    @Throws(IOException::class)
    override fun intercept(req: HttpRequest, body: ByteArray, ex: ClientHttpRequestExecution): ClientHttpResponse {
        val sessionNumber = makeSessionNumber()
        printRequest(sessionNumber, req, body)
        val response = ex.execute(req, body)
        printResponse(sessionNumber, response)
        return response
    }

    private fun makeSessionNumber(): String {
        return (Math.random() * 1000000).toInt().toString()
    }

    private fun printRequest(sessionNumber: String, req: HttpRequest, body: ByteArray) {
        log.info(
            "[{}] URI: {}, Method: {}, Headers:{}, Body:{} ",
            sessionNumber, req.uri, req.method, req.headers, String(body, StandardCharsets.UTF_8)
        )
    }

    @Throws(IOException::class)
    private fun printResponse(sessionNumber: String, res: ClientHttpResponse) {
        val body = BufferedReader(InputStreamReader(res.body, StandardCharsets.UTF_8)).lines()
            .collect(Collectors.joining("\n"))
        log.info(
            "[{}] Status: {}, Headers:{}, Body:{} ",
            sessionNumber, res.statusCode, res.headers, body
        )
    }
}

