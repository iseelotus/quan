package com.iseelotus.accountservice

import com.iseelotus.accountservice.config.ServerConfig
import com.iseelotus.accountservice.domains.Person
import com.iseelotus.accountservice.events.models.PersonChangeModel
import com.iseelotus.accountservice.utils.UserContextInterceptor
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate
import java.util.*

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
class AccountServiceApplication(private val serverConfig: ServerConfig) {
    @Bean
    fun getRestTemplate(): RestTemplate {
        val template = RestTemplate()
        val interceptors = template.interceptors
        if (interceptors == null) {
            template.interceptors = Collections.singletonList(UserContextInterceptor() as ClientHttpRequestInterceptor)
        } else {
            interceptors.add(UserContextInterceptor())
            template.interceptors = interceptors
        }
        return template
    }

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val jedisStandAloneConfig = RedisStandaloneConfiguration(serverConfig.getRedisServer(),
                serverConfig.getRedisPort())
        return JedisConnectionFactory(jedisStandAloneConfig)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Person> {
        val template = RedisTemplate<String, Person>()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AccountServiceApplication::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<AccountServiceApplication>(*args)
}

