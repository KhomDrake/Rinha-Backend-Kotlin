package com.vinicius.rinhabackendkotlin.controllers

import com.vinicius.rinhabackendkotlin.domain.model.UserEntity
import com.vinicius.rinhabackendkotlin.models.TransactionBody
import com.vinicius.rinhabackendkotlin.services.ClientService
import com.vinicius.rinhabackendkotlin.services.TransactionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
        return ResponseEntity.ok(clientService.createTransaction(id, body))
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
        return ResponseEntity.ok(transactionService.findAllByUser(user))
    }

}