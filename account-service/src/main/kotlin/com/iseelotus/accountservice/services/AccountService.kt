package com.iseelotus.accountservice.services

import com.iseelotus.accountservice.domains.Account
import com.iseelotus.accountservice.domains.AccountRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(private val accountRepository: AccountRepository) {
    fun getAccountByAccountNumber(accountNumber: String): Account? {
        return accountRepository.findAccountByAccountNumber(accountNumber)
    }

    fun getAccount(id: String): Account? {
        return accountRepository.findById(id).orElse(null)
    }

    fun addAccount(account: Account): Account {
        account.id = UUID.randomUUID().toString()
        accountRepository.save(account)
        return account
    }

    fun deleteAccount(account: Account) {
        accountRepository.delete(account)
    }

    fun getAllAccounts(): List<Account> {
        return accountRepository.findAll().toList()
    }
}