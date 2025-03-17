package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.service.UserService


class UserServiceImpl: UserService {

    var allUsers: MutableList<UserDto> = mutableListOf()

    override fun getUserById(userId: Int): UserDto {
        return allUsers.find { userId == it.id } ?: throw Exception("given ${userId} does not match with any existing user")
    }
    override fun getUsers(): MutableList<UserDto> = allUsers

    override fun createUser(user: UserDto): Int {
        allUsers.add(user)
       return 1
    }

    override fun deleteUser(userId: Int): Int {
        val user = allUsers.find  { userId == it.id }
        if (user != null) {
            allUsers.remove(user)
            return 1
        }
        return 0
    }

}