package com.iseelotus.accountservice.events.handlers

import com.iseelotus.accountservice.AccountServiceApplication
import com.iseelotus.accountservice.domains.PersonRedisRepository
import com.iseelotus.accountservice.events.models.PersonChangeModel
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class PersonChangeHandler (private val personRedisRepository: PersonRedisRepository) {
    companion object {
        private val logger = LoggerFactory.getLogger(PersonChangeHandler::class.java)
    }

    @StreamListener(Sink.INPUT)
    fun loggerSink(personChange: PersonChangeModel) {
        logger.info("Received an event for person id ${personChange.personId}")
        when (personChange.action) {
            "GET" -> logger.info("Received GET for person ${personChange.personId}")
            "SAVE" -> logger.info("Received SAVE for person ${personChange.personId}")
            "UPDATE" -> {
                logger.info("Received UPDATE for person ${personChange.personId}")
                personRedisRepository.deletePerson(personChange.personId)
            }
            "DELETE" -> {
                logger.info("Received DELETE for person ${personChange.personId}")
                personRedisRepository.deletePerson(personChange.personId)
            }
            else -> logger.info("Received an UNKOWN event ${personChange.action} for person ${personChange.personId}")
        }
    }
}
