package com.ale.cohabit.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Community(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val name: String,
    val creatorUsername: String,

    @OneToMany(mappedBy = "community", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tasks: MutableList<Task>?,

    @OneToMany(mappedBy = "community", cascade = [CascadeType.ALL], orphanRemoval = true)
    var shoppingLists: MutableList<ShoppingList>?,

    @OneToMany(mappedBy = "community", cascade = [CascadeType.PERSIST], orphanRemoval = false)
    var userIds: MutableList<User>?
)
