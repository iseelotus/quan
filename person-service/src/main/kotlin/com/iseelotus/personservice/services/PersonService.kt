package com.iseelotus.personservice.services

import com.iseelotus.personservice.domains.Person
import com.iseelotus.personservice.domains.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService {
    @Autowired
    private lateinit var personRepository: PersonRepository

    fun getPerson(id: String): Person? {
        return personRepository.findById(id).orElse(null)
    }

    fun addPerson(person: Person): Person {
        person.id = UUID.randomUUID().toString()
        personRepository.save(person)
        return person
    }

    fun updatePerson(person: Person) {
        personRepository.save(person)
    }

    fun deletePerson(person: Person) {
        personRepository.delete(person)
    }

    fun getAllPersons(): List<Person> {
        return personRepository.findAll().toList()
    }

}
