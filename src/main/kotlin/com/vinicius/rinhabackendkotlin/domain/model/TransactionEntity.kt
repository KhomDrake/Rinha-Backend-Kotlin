package com.vinicius.rinhabackendkotlin.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

fun String.isDebit() = lowercase() == "d"

fun String.isCredit() = lowercase() == "c"

@Entity(name = "transaction")
data class TransactionEntity(

    @Column(updatable = false)
    val valor: Int,

    @Column(nullable = false, length = 1, updatable = false)
    val tipo: String,

    @Column(nullable = false, length = 10)
    val descricao: String,

    val userId: Int,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "realizada_em", updatable = false)
    val realizadaEm: LocalDateTime = LocalDateTime.now(),

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0
)