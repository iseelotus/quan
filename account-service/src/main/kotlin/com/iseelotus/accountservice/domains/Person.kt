package com.iseelotus.accountservice.domains

import java.io.Serializable

data class Person(val id: String,
                  val familyName: String,
                  val givenName: String) : Serializable
