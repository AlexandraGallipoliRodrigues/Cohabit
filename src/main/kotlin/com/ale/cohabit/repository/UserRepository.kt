package com.ale.cohabit.repository

import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserRepository: JpaRepository<User, Int> {
    fun findByUsername(name: String): User?

    fun findByCommunityId(id: Int): List<User?>

    @Query("SELECT new com.ale.cohabit.dto.UserDto(u.id, u.name, u.surname, u.username, u.email, u.community.name, u.community.id) FROM User u WHERE u.username = ?1")
    fun findUserDtoByUsername(username: String): UserDto?
}