package com.vinicius.rinhabackendkotlin.models

import com.vinicius.rinhabackendkotlin.domain.model.isCredit
import com.vinicius.rinhabackendkotlin.domain.model.isDebit

class TransactionBody(
    val valor: Int,
    val tipo: String,
    val descricao: String
) {
    fun isValid() = tipo.isDebit() || tipo.isCredit()
}