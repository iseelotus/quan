package com.iseelotus.personservice.event.source

import com.iseelotus.personservice.event.models.PersonChangeModel
import com.iseelotus.personservice.utils.UserContext
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class SourceBean (private val source: Source) {
    companion object {
        private val logger = LoggerFactory.getLogger(SourceBean::class.java)
    }

    fun publishPersonChange(action: String, personId: String) {
        logger.info("Sending Kafka message $action for person with id $personId")
        val change = PersonChangeModel(
                PersonChangeModel::class.java.typeName,
                action,
                personId,
                UserContext.CORRELATION_ID
        )
        source.output().send(
                MessageBuilder.withPayload(change).build()
        )
    }
}