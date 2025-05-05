package com.ale.cohabit.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    var name: String?,
    var username: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")  // Nombre de la columna FK en la tabla user
    var community: Community? = null
)
