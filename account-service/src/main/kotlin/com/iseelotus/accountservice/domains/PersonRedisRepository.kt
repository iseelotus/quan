package com.iseelotus.accountservice.domains

interface PersonRedisRepository {
    fun savePerson(person: Person)
    fun updatePerson(person: Person)
    fun deletePerson(personId: String)
    fun findPerson(personId: String): Person
}
