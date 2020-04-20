package com.iseelotus.accountservice.utils

data class UserContext(
        var correlationId: String = String(),
        var authToken: String = String(),
        var userId: String = String(),
        var accountId: String = String()
) {
    companion object {
        const val CORRELATION_ID = "tmx-correlation-id"
        const val AUTH_TOKEN = "tmx-auth-token"
        const val USER_ID = "tmx-user-id"
        const val ACCOUNT_ID = "tmx-org-id"
    }
}