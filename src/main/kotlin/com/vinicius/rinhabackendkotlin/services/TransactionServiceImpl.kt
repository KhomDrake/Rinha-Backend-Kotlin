package com.vinicius.rinhabackendkotlin.services

import com.vinicius.rinhabackendkotlin.controllers.dto.SaldoDto
import com.vinicius.rinhabackendkotlin.controllers.dto.StatementDto
import com.vinicius.rinhabackendkotlin.controllers.dto.TransactionDto
import com.vinicius.rinhabackendkotlin.domain.repository.TransactionRepository
import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import com.vinicius.rinhabackendkotlin.domain.model.UserEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionServiceImpl(private val repository: TransactionRepository) : TransactionService {

    @Transactional
    override fun findAllByUser(user: UserEntity): StatementDto {
        val transactions = repository.findByUserIdOrderByRealizadaEmDesc(user.id)
        return StatementDto(
            saldo = SaldoDto(
                user.saldo,
                user.limite
            ),
            ultimas_transacoes = transactions.map { TransactionDto(it) }
        )
    }

    @Transactional
    override fun saveTransaction(transactionEntity: TransactionEntity): TransactionEntity = repository.save(transactionEntity)

}