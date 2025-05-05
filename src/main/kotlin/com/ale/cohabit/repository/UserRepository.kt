package com.ale.cohabit.repository

import com.ale.cohabit.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByUsername(name: String): User
}