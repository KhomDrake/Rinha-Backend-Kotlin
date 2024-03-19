package com.vinicius.rinhabackendkotlin.services

import com.vinicius.rinhabackendkotlin.controllers.dto.TransactionDto
import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import com.vinicius.rinhabackendkotlin.domain.repository.UserRepository
import com.vinicius.rinhabackendkotlin.domain.model.UserEntity
import com.vinicius.rinhabackendkotlin.domain.model.isCredit
import com.vinicius.rinhabackendkotlin.domain.model.isDebit
import com.vinicius.rinhabackendkotlin.exception.NotFoundUserException
import com.vinicius.rinhabackendkotlin.exception.TransactionNotValidException
import com.vinicius.rinhabackendkotlin.models.TransactionBody
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClientServiceImpl(
    private val repository: UserRepository,
    private val transactionService: TransactionService
) : ClientService {

    @Transactional
    override fun createTransaction(id: Int, body: TransactionBody) : TransactionDto {
        if(!body.isValid()) throw TransactionNotValidException()

        val user = findUserById(id)

        if(body.tipo.isDebit()) {
            val newSaldo = user.saldo + body.valor

            if (Math.abs(newSaldo) > user.limite) throw TransactionNotValidException()

            user.saldo = newSaldo
        }

        if(body.tipo.isCredit()) {
            val newCredit = user.limite - body.valor

            if (newCredit < 0) throw TransactionNotValidException()

            user.limite = newCredit
        }

        val newTransaction = TransactionEntity(
            valor = body.valor,
            tipo = body.tipo,
            descricao = body.descricao,
            userId = user.id
        )
        transactionService.saveTransaction(newTransaction)
        saveUser(user)

        return TransactionDto(newTransaction)
    }

    @Transactional(readOnly = true)
    override fun allUsers() = repository.findAll()

    @Transactional(readOnly = true)
    override fun findUserById(id: Int) = repository.findById(id).orElseThrow {
        NotFoundUserException()
    }

    @Transactional
    override fun saveUser(newUser: UserEntity) = repository.save(newUser)

}