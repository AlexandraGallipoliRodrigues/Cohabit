package com.ale.cohabit.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
data class ShoppingList(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val name: String,

    var checked: Boolean,

    @OneToMany(mappedBy = "shoppingList", cascade = [CascadeType.ALL], orphanRemoval = true)
    var elements: MutableList<ShoppingElement>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    var community: Community?,


)
