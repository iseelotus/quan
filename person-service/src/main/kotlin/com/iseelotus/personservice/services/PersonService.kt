package com.iseelotus.personservice.services

import com.iseelotus.personservice.domains.Person
import com.iseelotus.personservice.domains.PersonRepository
import com.iseelotus.personservice.event.source.SourceBean
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(
        private val personRepository: PersonRepository,
        private val sourceBean: SourceBean
) {

    fun getPerson(id: String): Person? {
        return personRepository.findById(id).orElse(null)
    }

    fun addPerson(person: Person): Person {
        person.id = UUID.randomUUID().toString()
        personRepository.save(person)
        sourceBean.publishPersonChange("SAVE", person.id)
        return person
    }

    fun updatePerson(person: Person): Person {
        val person = personRepository.save(person)
        sourceBean.publishPersonChange("UPDATE", person.id)
        return person
    }

    fun deletePerson(person: Person) {
        personRepository.delete(person)
    }

    fun getAllPersons(): List<Person> {
        return personRepository.findAll().toList()
    }

}
