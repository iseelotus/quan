package com.iseelotus.accountservice.services

import com.iseelotus.accountservice.clients.PersonFeignClient
import com.iseelotus.accountservice.clients.PersonRestTemplateClient
import com.iseelotus.accountservice.domains.Account
import com.iseelotus.accountservice.domains.AccountRepository
import com.iseelotus.accountservice.domains.Person
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(private val accountRepository: AccountRepository,
                     private val personRestTemplateClient: PersonRestTemplateClient) {
    @HystrixCommand(commandProperties =
    [HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")]
    )
    fun getAccount(accountId: String): Account? {
        val account = accountRepository.findById(accountId).orElse(null)
        val person = personRestTemplateClient.getPerson(account.personId)
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

    fun getAccountByAccountNumber(accountNumber: String): Account? {
        return accountRepository.findAccountByAccountNumber(accountNumber)
    }
}