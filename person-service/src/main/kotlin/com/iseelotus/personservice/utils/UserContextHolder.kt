package com.iseelotus.personservice.utils

import org.springframework.util.Assert

class UserContextHolder {
    companion object {
        private val userContext = ThreadLocal<UserContext>()
        fun getContext(): UserContext {
            var context = userContext.get()
            if (context == null) {
                context = UserContext()
                userContext.set(context)
            }
            return userContext.get()
        }

        fun setContext(context: UserContext) {
            Assert.notNull(context, "UserContext should not be null")
            userContext.set(context)
        }
    }
}
