package com.iseelotus.accountservice.events.models

data class PersonChangeModel(
        val type: String,
        val action: String,
        val personId: String,
        val correlationId: String?
)