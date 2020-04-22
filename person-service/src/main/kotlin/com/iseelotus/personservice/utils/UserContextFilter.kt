package com.iseelotus.personservice.utils

import com.iseelotus.personservice.utils.UserContext
import com.iseelotus.personservice.utils.UserContextHolder
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class UserContextFilter: Filter {
    override fun doFilter(p0: ServletRequest?, p1: ServletResponse?, p2: FilterChain?) {
        val httpServletRequest = p0 as HttpServletRequest
        UserContextHolder.getContext().correlationId = httpServletRequest.getHeader(UserContext.CORRELATION_ID)
        UserContextHolder.getContext().authToken = httpServletRequest.getHeader(UserContext.AUTH_TOKEN)
        UserContextHolder.getContext().userId = httpServletRequest.getHeader(UserContext.USER_ID)

        logger.debug("Account Service Incoming Correlation id: {}.", httpServletRequest.getHeader(UserContext.CORRELATION_ID))
        p2?.doFilter(httpServletRequest, p1)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserContextFilter::class.java)
    }
}