package com.vinicius.rinhabackendkotlin.services

import com.vinicius.rinhabackendkotlin.controllers.dto.TransactionDto
import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import com.vinicius.rinhabackendkotlin.domain.model.UserEntity
import com.vinicius.rinhabackendkotlin.models.TransactionBody

interface ClientService {

    fun createTransaction(id: Int, body: TransactionBody): TransactionDto

    fun allUsers() : List<UserEntity>

    fun findUserById(id: Int) : UserEntity

    fun saveUser(newUser: UserEntity) : UserEntity

}