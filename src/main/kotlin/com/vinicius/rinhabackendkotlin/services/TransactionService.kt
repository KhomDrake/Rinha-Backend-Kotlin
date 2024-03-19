package com.vinicius.rinhabackendkotlin.services

//import com.vinicius.rinhabackendkotlin.domain.repository.TransactionRepository
//import com.vinicius.rinhabackendkotlin.domain.repository.UserRepository
import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity

interface TransactionService {

    fun findAllByUserId(id: Int): List<TransactionEntity>

    fun saveTransaction(transactionEntity: TransactionEntity): TransactionEntity

}