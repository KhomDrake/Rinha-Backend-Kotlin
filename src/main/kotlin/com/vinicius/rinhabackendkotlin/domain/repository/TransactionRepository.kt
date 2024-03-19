package com.vinicius.rinhabackendkotlin.domain.repository

import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: JpaRepository<TransactionEntity, Int> {

    fun findByUserIdOrderByRealizadaEmDesc(id: Int): List<TransactionEntity>

    fun findAllById(id: Int): List<TransactionEntity>

}