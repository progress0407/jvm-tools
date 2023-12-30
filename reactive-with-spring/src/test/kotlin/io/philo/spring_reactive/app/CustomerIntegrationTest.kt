package io.philo.spring_reactive.app

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerIntegrationTest {

    @Autowired
    private lateinit var repository: CustomerRepository

    @Autowired
    private lateinit var webClient: WebTestClient

    @Test
    fun `한 건 찾기 테스트`() {
        repository.saveAll<Customer>(
            listOf(
                Customer("Jack Bauer"),
                Customer("Chloe O'Brian"),
                Customer("Kim Bauer"),
                Customer("David Palmer"),
                Customer("Michelle Dessler")
            )
        )
            .blockLast(Duration.ofSeconds(10))

//        val customers: Flux<Customer?> = customerRepository.findAll()
//        customers.subscribe{ println("data: $it") }

        val data = repository.findAll().log()
        println("data = ${data}")

        webClient.get().uri("/customer/{id}", 1L)
            .exchange()
            .expectBody()
            .jsonPath("$.name").isEqualTo("Jack Bauer")
    }
}