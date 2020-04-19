package com.iseelotus.accountservice.services

import com.iseelotus.accountservice.clients.PersonFeignClient
import com.iseelotus.accountservice.domains.Account
import com.iseelotus.accountservice.domains.AccountRepository
import com.iseelotus.accountservice.domains.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(private val accountRepository: AccountRepository,
                     private val personFeignClient: PersonFeignClient) {
    fun getAccount(accountId: String): Account? {
        val account = accountRepository.findById(accountId).orElse(null)
        val person = personFeignClient.getPerson(account.personId)
        if (account != null && person != null) {
            account.personFamilyName = person.familyName
            account.personGivenName = person.givenName
        }
        return account
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