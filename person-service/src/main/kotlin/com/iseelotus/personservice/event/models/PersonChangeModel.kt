package com.iseelotus.personservice.event.models

data class PersonChangeModel(
        val type: String,
        val action: String,
        val personId: String,
        val correlationId: String?
)