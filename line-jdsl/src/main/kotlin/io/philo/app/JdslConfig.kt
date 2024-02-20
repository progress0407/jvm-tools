package io.philo.app

import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JdslConfig {

    @Bean
    fun jpqlRenderContext() = JpqlRenderContext()

    @Bean
    fun jpqlRenderer() = JpqlRenderer()
}