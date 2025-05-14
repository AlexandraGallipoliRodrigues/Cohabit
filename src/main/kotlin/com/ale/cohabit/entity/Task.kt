package com.ale.cohabit.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var name: String,
    var checked: Boolean,
    var deadline: String,
    var assignee: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    var community: Community?
)
