package com.iseelotus.accountservice.domains

import org.springframework.data.repository.CrudRepository

interface AccountRepository: CrudRepository<Account, String> {
    fun findAccountByAccountNumber(accountNumber: String): Account?
}