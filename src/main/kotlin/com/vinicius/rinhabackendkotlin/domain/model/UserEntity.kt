package com.vinicius.rinhabackendkotlin.domain.model

import jakarta.persistence.*

@Entity(name = "clientes")
data class UserEntity(
    @Id
    var id: Int,
    @Column(name = "saldo")
    var saldo: Int,
    @Column(name = "limite")
    var limite: Int
)