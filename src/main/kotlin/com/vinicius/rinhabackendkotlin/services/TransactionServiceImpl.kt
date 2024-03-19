package com.vinicius.rinhabackendkotlin.services

import com.vinicius.rinhabackendkotlin.domain.repository.TransactionRepository
import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionServiceImpl(private val repository: TransactionRepository) : TransactionService {

    @Transactional
    override fun findAllByUserId(id: Int): List<TransactionEntity> {
        return repository.findByUserIdOrderByRealizadaEmDesc(id)
    }

    @Transactional
    override fun saveTransaction(transactionEntity: TransactionEntity): TransactionEntity = repository.save(transactionEntity)

}