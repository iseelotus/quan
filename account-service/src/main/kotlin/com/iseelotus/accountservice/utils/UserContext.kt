package com.iseelotus.accountservice.utils

import org.springframework.stereotype.Component

@Component
data class UserContext(
        var correlationId: String = String()
) {
    companion object {
        val CORRELATION_ID = "tmx-correlation-id"
    }
}