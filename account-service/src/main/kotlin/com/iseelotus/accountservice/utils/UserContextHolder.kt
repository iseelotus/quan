package com.iseelotus.accountservice.utils

import org.springframework.util.Assert

class UserContextHolder {
    companion object {
        private val userContext = ThreadLocal<UserContext>()

        fun getContext(): UserContext {
            var context = userContext.get()
            if (context == null) {
                userContext.set(UserContext())
            }
            return userContext.get()
        }

        fun setContext(context: UserContext) {
            Assert.notNull(context, "Null UserContext instance not permitted")
            userContext.set(context)
        }
    }
}