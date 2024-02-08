package io.philo.app

import mu.KotlinLogging
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

class LoggingInterceptor : ClientHttpRequestInterceptor {

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