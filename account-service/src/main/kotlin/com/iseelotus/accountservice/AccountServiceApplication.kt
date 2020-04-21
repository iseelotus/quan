package com.iseelotus.accountservice

import com.iseelotus.accountservice.utils.UserContextInterceptor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate
import java.util.*

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
class AccountServiceApplication {
	@LoadBalanced
	@Bean
	fun getRestTemplate(): RestTemplate {
		var template = RestTemplate()
		val interceptors = template.interceptors
		if (interceptors == null) {
			template.interceptors = Collections.singletonList(UserContextInterceptor() as ClientHttpRequestInterceptor)
		} else {
			interceptors.add(UserContextInterceptor())
			template.interceptors = interceptors
		}
		return template
	}

	fun main(args: Array<String>) {
		runApplication<AccountServiceApplication>(*args)
	}
}


