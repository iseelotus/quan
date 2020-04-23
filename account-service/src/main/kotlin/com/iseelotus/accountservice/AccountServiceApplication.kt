package com.iseelotus.accountservice

import com.iseelotus.accountservice.utils.UserContextInterceptor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.eureka.http.RestTemplateEurekaHttpClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.client.loadbalancer.LoadBalanced

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
class AccountServiceApplication {
	fun main(args: Array<String>) {
		runApplication<AccountServiceApplication>(*args)
	}

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
}


