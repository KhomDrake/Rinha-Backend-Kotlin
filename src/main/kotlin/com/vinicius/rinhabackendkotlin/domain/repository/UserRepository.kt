package com.vinicius.rinhabackendkotlin.domain.repository

import com.vinicius.rinhabackendkotlin.domain.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Int>