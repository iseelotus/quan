package com.iseelotus.accountservice.domains

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class PersonRedisRepositoryImpl: PersonRedisRepository {
    companion object {
        private val HASH_NAME = "person"
    }

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Person>
    private lateinit var hashOperations: HashOperations<String, String, Person>

    @PostConstruct
    private fun init() {
        hashOperations = redisTemplate.opsForHash()
    }

    override fun savePerson(person: Person) {
        hashOperations.put(HASH_NAME, person.id, person)
    }

    override fun updatePerson(person: Person) {
        hashOperations.put(HASH_NAME, person.id, person)
    }

    override fun deletePerson(personId: String) {
        hashOperations.delete(HASH_NAME, personId)
    }

    override fun findPerson(personId: String): Person {
        return hashOperations.get(HASH_NAME, personId) as Person
    }
}
