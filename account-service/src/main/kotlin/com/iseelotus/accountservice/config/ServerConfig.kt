package com.iseelotus.accountservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ServerConfig{
    @Value("\${redis.server}")
    private val redisServer: String= ""

    @Value("\${redis.port}")
    private val redisPort: Int = 0

    fun getRedisServer(): String {
        return redisServer
    }

    fun getRedisPort(): Int {
        return redisPort
    }
}
