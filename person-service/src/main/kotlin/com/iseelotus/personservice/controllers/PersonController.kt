package com.iseelotus.personservice.controllers

import com.iseelotus.personservice.domains.Person
import com.iseelotus.personservice.services.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/v1/persons")
class PersonController(private val personService: PersonService) {
    @GetMapping
    fun getPersons(): ResponseEntity<List<Person>> {
        val persons = personService.getAllPersons()
        return ResponseEntity.ok(persons)
    }

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable("id") id: String): ResponseEntity<Person> {
        val person = personService.getPerson(id)
        return if (person != null) {
            ResponseEntity.ok(person)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable("id") id: String): ResponseEntity<Void> {
        val person = personService.getPerson(id)
        return if (person != null) {
            personService.deletePerson(person)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun addPerson(@RequestBody person: Person): ResponseEntity<Person> {
        val person = personService.addPerson(person)
        return ResponseEntity.created(URI("/persons/"+person.id)).body(person)
    }

    @PutMapping
    fun updatePerson(@RequestBody person: Person): ResponseEntity<Person?> {
        val person = personService.updatePerson(person)
        return ResponseEntity.ok(person)
    }
}
