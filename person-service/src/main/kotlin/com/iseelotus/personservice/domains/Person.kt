package com.iseelotus.personservice.domains

import javax.persistence.*

@Entity
@Table(name = "persons")
data class Person (
    @Id
    var id: String,

    @Column(name = "family_name", nullable = false)
    val familyName: String,

    @Column(name = "given_name", nullable = false)
    val givenName: String
)