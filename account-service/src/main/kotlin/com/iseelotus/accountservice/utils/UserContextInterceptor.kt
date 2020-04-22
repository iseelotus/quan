package com.iseelotus.accountservice.utils

import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

class UserContextInterceptor: ClientHttpRequestInterceptor {
    override fun intercept(httpRequest: HttpRequest, body: ByteArray, clientHttpRequestExecution: ClientHttpRequestExecution): ClientHttpResponse {
        val headers = httpRequest.headers
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().correlationId)
        return clientHttpRequestExecution.execute(httpRequest, body)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserContextInterceptor::class.java)
    }
}