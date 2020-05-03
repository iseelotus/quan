package com.iseelotus.accountservice.clients

import com.iseelotus.accountservice.domains.Person
import com.iseelotus.accountservice.domains.PersonRedisRepository
import com.iseelotus.accountservice.utils.UserContextHolder
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Component
class PersonRestTemplateClient(private val restTemplate: RestTemplate,
                               private val personRedisRepo: PersonRedisRepository) {
    fun getPerson(personId: String): Person? {
        logger.info(">> In accountService.getPerson: {}. Thread.Id: {}",
                UserContextHolder.getContext().correlationId,
                Thread.currentThread().id)

        var person = checkCache(personId)
        if (person != null) {
            logger.info("Person $personId retrieved from redis cache $person")
            return person
        }

        logger.info("Person $personId not found in cache")

        val restExchange = restTemplate.exchange(
                "http://localhost:5555/api/person-service/v1/persons/{personId}",
                HttpMethod.GET, null, Person::class.java, personId)
        person = restExchange.body

        if (person !=null) {
            cachePerson(person)
        }
        return person
    }

    private fun checkCache(personId: String): Person? {
        return try {
            personRedisRepo.findPerson(personId)
        } catch (ex: Exception) {
            logger.error("Error encountered person $personId from redis cache with exception $ex")
            null
        }
    }

    private fun cachePerson(person: Person) {
        try {
            personRedisRepo.savePerson(person)
        } catch (ex: Exception) {
            logger.error("Error caching person ${person.id} in redis with exception $ex")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PersonRestTemplateClient::class.java)
    }
}
