package com.ale.cohabit.service

import com.ale.cohabit.dto.ResponseWrapper
import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.User
import java.util.concurrent.CompletableFuture

interface UserService {
    fun getUserById(userId: Int): UserDto?

    fun getUsers(): List<UserDto>

    fun createUser(user: UserDto): Int

    fun deleteUser(userId: Int)

    fun getUserByUsername(name: String): User?

    fun assignCommunityToUser(username: String, community: Community): User
}