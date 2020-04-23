package com.iseelotus.accountservice.clients

import com.iseelotus.accountservice.domains.Person
import com.iseelotus.accountservice.utils.UserContextHolder
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class PersonRestTemplateClient(private val restTemplate: RestTemplate) {
    fun getPerson(personId: String): Person? {
        logger.debug(">> In accountService.getPerson: {}. Thread.Id: {}",
                UserContextHolder.getContext().correlationId,
                Thread.currentThread().id)
        val restExchange = restTemplate.exchange(
                "http://localhost:5555/api/person-service/v1/persons/{personId}",
    companion object {
        private val logger = LoggerFactory.getLogger(PersonRestTemplateClient::class.java)
    }

}