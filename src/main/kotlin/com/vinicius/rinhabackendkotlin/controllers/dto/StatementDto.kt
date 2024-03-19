package com.vinicius.rinhabackendkotlin.controllers.dto

import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import java.time.LocalDateTime

class StatementDto(
    val saldo: SaldoDto,
    val ultimas_transacoes: List<TransactionDto>
)

class SaldoDto(
    val total: Int,
    val limite: Int,
    val data: String = LocalDateTime.now().toString()
)

class TransactionDto(
    val valor: Int,
    val tipo: String,
    val descripcao: String,
    val realizada_em: String
) {
    constructor(entity: TransactionEntity) : this(
        valor = entity.valor,
        tipo = entity.tipo,
        descripcao = entity.descricao,
        realizada_em = entity.realizadaEm.toString(),
    )
}