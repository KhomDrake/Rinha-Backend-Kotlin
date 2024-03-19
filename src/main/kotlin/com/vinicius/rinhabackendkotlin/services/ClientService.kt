package com.vinicius.rinhabackendkotlin.services

import com.vinicius.rinhabackendkotlin.domain.model.UserEntity

interface ClientService {

    fun allUsers() : List<UserEntity>

    fun findUserById(id: Int) : UserEntity?

    fun saveUser(newUser: UserEntity) : UserEntity

}