package com.iseelotus.personservice.utils

data class UserContext(
        var correlationId: String = String()
) {
    companion object {
        const val CORRELATION_ID = "tmx-correlation-id"
    }
}
