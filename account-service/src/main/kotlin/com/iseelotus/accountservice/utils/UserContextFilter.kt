package com.iseelotus.accountservice.utils

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class UserContextFilter: Filter {
    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, filterChain: FilterChain?) {
        val httpServletRequest = servletRequest as HttpServletRequest
        UserContextHolder.getContext().correlationId = httpServletRequest.getHeader(UserContext.CORRELATION_ID)
        logger.debug("Account service incoming correlation id: {}", UserContextHolder.getContext().correlationId)
        filterChain?.doFilter(httpServletRequest, servletResponse)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserContextFilter::class.java)
    }
}