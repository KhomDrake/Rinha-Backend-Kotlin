package com.vinicius.rinhabackendkotlin.services

//import com.vinicius.rinhabackendkotlin.domain.repository.TransactionRepository
//import com.vinicius.rinhabackendkotlin.domain.repository.UserRepository
import com.vinicius.rinhabackendkotlin.controllers.dto.StatementDto
import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import com.vinicius.rinhabackendkotlin.domain.model.UserEntity

interface TransactionService {

    fun findAllByUser(userEntity: UserEntity): StatementDto

    fun saveTransaction(transactionEntity: TransactionEntity): TransactionEntity

}