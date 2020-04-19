package com.iseelotus.accountservice.controllers

import com.iseelotus.accountservice.domains.Account
import com.iseelotus.accountservice.services.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {
    @GetMapping
    fun getAccounts(): ResponseEntity<List<Account>> {
        val accounts = accountService.getAllAccounts()
        return if (accounts.isNotEmpty()) {
            ResponseEntity.ok(accounts)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @PostMapping
    fun addAccount(@RequestBody account:Account): ResponseEntity<Account> {
        val account = accountService.addAccount(account)
        return ResponseEntity.created(URI("/accounts/"+account.id)).body(account)
    }

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable("id") id: String): ResponseEntity<Void> {
        val account = accountService.getAccount(id)
        return if (account != null) {
            accountService.deleteAccount(account)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable("id") id: String): ResponseEntity<Account> {
        val account = accountService.getAccount(id)
        return if (account != null) {
            ResponseEntity.ok(account)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
