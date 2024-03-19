package com.vinicius.rinhabackendkotlin.services

import com.vinicius.rinhabackendkotlin.domain.repository.UserRepository
import com.vinicius.rinhabackendkotlin.domain.model.UserEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class ClientServiceImpl(private val repository: UserRepository) : ClientService {

    @Transactional(readOnly = true)
    override fun allUsers() = repository.findAll()

    @Transactional(readOnly = true)
    override fun findUserById(id: Int) = repository.findById(id).getOrNull()

    @Transactional
    override fun saveUser(newUser: UserEntity) = repository.save(newUser)

}