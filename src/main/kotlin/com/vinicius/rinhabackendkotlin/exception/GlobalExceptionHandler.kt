package com.vinicius.rinhabackendkotlin.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(NotFoundUserException::class)
    fun handleNotFoundUserException(ex: NotFoundUserException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(TransactionNotValidException::class)
    fun handleTransactionNotValidException(ex: TransactionNotValidException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(Throwable::class)
    fun handleUnexpectedException(unexpectedException: Throwable): ResponseEntity<String> {
        val message = "Unexpected server error."
        logger.error(message, unexpectedException)
        return ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}