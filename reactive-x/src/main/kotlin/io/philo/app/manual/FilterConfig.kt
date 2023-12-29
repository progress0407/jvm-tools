package io.philo.app.manual

import jakarta.servlet.Filter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun filter(): FilterRegistrationBean<Filter> {

        val bean = FilterRegistrationBean<Filter>(ReactiveFilter())
        bean.addUrlPatterns("/add")

        return bean
    }

    @Bean
    fun filter2(eventNotify: EventNotify): FilterRegistrationBean<Filter> {

        val bean = FilterRegistrationBean<Filter>(ReactiveFilter2(eventNotify))
        bean.addUrlPatterns("/sse")

        return bean
    }
}