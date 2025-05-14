package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.User
import com.ale.cohabit.repository.UserRepository
import com.ale.cohabit.service.implementations.mapper.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val mapper: Mapper
) {

     fun getUserById(userId: Int): UserDto {
        val users = userRepository.findById(userId)
        if (null == users.get().id) {
            throw Exception("no se ha podido encontrar el usario con el uid ${userId}")
        } else return mapper.mapUserToDto(users.get())

    }
     fun getUsers(): List<UserDto> = userRepository.findAll()
         .map { mapper.mapUserToDto(it) }
         .toMutableList()

    @Transactional
     fun createUser(userDto: UserDto): Int {
        var user = User(
            id = null,
            name = userDto.name,
            surname = userDto.name,
            username = userDto.username,
            email = userDto.email,
            community = null,
        )
        try {
            user = userRepository.save(user)
        } catch (e: Exception) {
            throw Exception("No se ha podido crear el usuario ${user.name}", e)
        }
        return user.id  ?: throw Exception("no se ha podido crear el usuario ${user.name}")
    }

     fun deleteUser(userId: Int) {
         try {
             userRepository.deleteById(userId)
         } catch (e: Exception) {
             throw Exception("No se ha podido eliminar el usuario ${userId}", e)
         }
    }

     fun getUserByUsername(username: String): UserDto {
        val user = userRepository.findUserDtoByUsername(username)
        if (user == null) {
            throw Exception("no se ha podido encontrar el usario con el nombre ${username}")
        } else return user
    }

    fun deleteUserFromCommunity(username: String, communityId: Int) {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            user.community = null
            userRepository.save(user)
        }
    }

    fun getUsersByCommunityId(communityId: Int): List<User?> {
        return userRepository.findByCommunityId(communityId)
    }

    fun findByUsername(username: String): User? = userRepository.findByUsername(username)

    fun updateUser(user: User) {
        userRepository.save(user)
    }

}