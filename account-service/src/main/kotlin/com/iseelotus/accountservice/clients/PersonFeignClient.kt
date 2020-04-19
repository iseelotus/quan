package com.iseelotus.accountservice.clients

import com.iseelotus.accountservice.domains.Person
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("person-service")
interface PersonFeignClient {
    @RequestMapping(
            method = [RequestMethod.GET],
            value = ["/v1/persons/{personId}"],
            consumes=["application/json"]
    )
    fun getPerson(@PathVariable("personId") personId: String): Person?
}
