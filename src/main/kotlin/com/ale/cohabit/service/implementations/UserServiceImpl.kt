package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.User
import com.ale.cohabit.repository.UserRepository
import com.ale.cohabit.service.UserService
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    var allUsers: MutableList<UserDto> = mutableListOf()

    override fun getUserById(userId: Int): UserDto? = mapCommunityToDto(userRepository.findById(userId).getOrNull())

    override fun getUsers(): List<UserDto> = userRepository.findAll() as List<UserDto>

    override fun createUser(userDto: UserDto): Int {
        var user = User(id = null, name = userDto.name, username = userDto.username)
        user.name = userDto.name.toString()
        user = userRepository.save(user)

        return user.id ?: -1
    }

    override fun deleteUser(userId: Int) {
        userRepository.deleteById(userId)
    }

    override fun getUserByUsername(username: String): User? = userRepository.findByUsername(username)

    override fun assignCommunityToUser(username: String, community: Community): User {
        var user = getUserByUsername(username)
        user?.community = community
        return userRepository.save(user!!)
    }

    fun mapCommunityToDto(user: User?): UserDto {
        return UserDto(name = user?.name, comunidad = user?.community?.name)
    }
}