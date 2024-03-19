package com.vinicius.rinhabackendkotlin.controllers

import com.vinicius.rinhabackendkotlin.controllers.dto.SaldoDto
import com.vinicius.rinhabackendkotlin.controllers.dto.StatementDto
import com.vinicius.rinhabackendkotlin.controllers.dto.TransactionDto
import com.vinicius.rinhabackendkotlin.models.TransactionBody
import com.vinicius.rinhabackendkotlin.domain.model.TransactionEntity
import com.vinicius.rinhabackendkotlin.domain.model.UserEntity
import com.vinicius.rinhabackendkotlin.domain.model.isCredit
import com.vinicius.rinhabackendkotlin.domain.model.isDebit
import com.vinicius.rinhabackendkotlin.services.ClientService
import com.vinicius.rinhabackendkotlin.services.TransactionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients")
class ClientController(
    private val clientService: ClientService,
    private val transactionService: TransactionService
) {

    @PostMapping("{id}/transacoes")
    @Operation(summary = "Add Transaction", description = "Add transaction to user")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful")
    ])
    fun addTransaction(
        @PathVariable id: Int,
        @RequestBody body: TransactionBody
    ): ResponseEntity<*> {
        if(!body.isValid()) return ResponseEntity(
            null,
            HttpStatus.UNPROCESSABLE_ENTITY
        )

        val user = clientService.findUserById(id)
            ?: return ResponseEntity(
                null,
                HttpStatus.NOT_FOUND
            )

        if(body.tipo.isDebit()) {
            val newSaldo = user.saldo + body.valor

            if (Math.abs(newSaldo) > user.limite) return ResponseEntity(
                null,
                HttpStatus.UNPROCESSABLE_ENTITY
            )

            user.saldo = newSaldo
        }

        if(body.tipo.isCredit()) {
            val newCredit = user.limite - body.valor

            if (newCredit < 0) return ResponseEntity(
                null,
                HttpStatus.UNPROCESSABLE_ENTITY
            )

            user.limite = newCredit
        }

        val newTransaction = TransactionEntity(
            valor = body.valor,
            tipo = body.tipo,
            descricao = body.descricao,
            userId = user.id
        )
        transactionService.saveTransaction(newTransaction)
        clientService.saveUser(user)
        return ResponseEntity.ok(Unit)
    }

    @GetMapping
    @Operation(summary = "Return users", description = "All users")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful")
    ])
    fun users(): ResponseEntity<List<UserEntity>> {
        return ResponseEntity.ok(clientService.allUsers())
    }

    @GetMapping("{id}/extrato")
    @Operation(summary = "Return transactions", description = "All transactions from a single user")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation successful")
    ])
    fun statementByUser(
        @PathVariable id: Int,
    ): ResponseEntity<*> {
        val user = clientService.findUserById(id)
            ?: return ResponseEntity(
                null,
                HttpStatus.NOT_FOUND
            )
        val transactions = transactionService.findAllByUserId(id).take(10)
        val data = StatementDto(
            saldo = SaldoDto(
                user.saldo,
                user.limite
            ),
            ultimas_transacoes = transactions.map { TransactionDto(
                it.valor, it.tipo, it.descricao, it.realizadaEm.toString()
            ) }
        )
        return ResponseEntity.ok(data)
    }

}