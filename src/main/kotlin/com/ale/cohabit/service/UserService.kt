package com.ale.cohabit.service

import com.ale.cohabit.dto.UserDto

interface UserService {
    fun getUserById(userId: Int): UserDto

    fun getUsers(): MutableList<UserDto>

    fun createUser(user: UserDto): Int

    fun deleteUser(userId: Int): Int
}