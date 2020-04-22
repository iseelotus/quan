package com.iseelotus.personservice.utils

data class UserContext(
        var correlationId: String = String(),
        var authToken: String = String(),
        var userId: String = String()
) {
    companion object {
        const val CORRELATION_ID = "tmx-correlation-id"
        const val AUTH_TOKEN = "tmx-auth-token"
        const val USER_ID = "tmx-user-id"
    }
}