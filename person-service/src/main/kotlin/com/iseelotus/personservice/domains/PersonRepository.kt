package com.iseelotus.personservice.domains

import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, String> {
}
