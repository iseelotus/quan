package com.iseelotus.accountservice.utils

data class UserContext(
        var correlationId: String = "",
        var authToken: String = "",
        var userId: String = ""
) {
    companion object {
        const val CORRELATION_ID = "tmx-correlation-id"
        const val AUTH_TOKEN = "tmx-auth-token"
        const val USER_ID = "tmx-user-id"
    }
}
