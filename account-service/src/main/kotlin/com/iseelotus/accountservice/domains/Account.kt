package com.iseelotus.accountservice.domains

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "accounts")
data class Account(
       @Id
       var id: String,
       @Column(name="person_id")
       val personId: String,

       @Column(name="account_number")
       val accountNumber: String,

       val balance: BigDecimal
)
