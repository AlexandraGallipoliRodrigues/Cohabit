package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.User
import com.ale.cohabit.repository.UserRepository
import com.ale.cohabit.service.implementations.mapper.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val mapper: Mapper,
    private val communityServiceImpl: CommunityServiceImpl
) {

    var allUsers: MutableList<UserDto> = mutableListOf()

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
        user.name = userDto.name.toString()
        //user = userRepository.save(user)
        
        //if (user.id == -1) {
        //    throw Exception("no se ha podido crear el usuario")
        //}
         val communityId = communityServiceImpl.createCommunity(
             CommunityDto(
                 id = null,
                 name = userDto.communityName,
                 creatorUsername = userDto.username,
                 tasks =  mutableListOf(),
                 userIds =  mutableListOf(),
                 shoppingLists =  mutableListOf()
             )
         )
        user.community = communityId
        user = userRepository.save(user)
        return user.id!!
    }

     fun deleteUser(userId: Int) {
        userRepository.deleteById(userId)
    }

     fun getUserByUsername(username: String): User {
        val user = userRepository.findByUsername(username)
        if (user == null) {
            throw Exception("no se ha podido encontrar el usario con el nombre ${username}")
        } else return user
    } 

     fun assignCommunityToUser(username: String, communityId: Int): User {
        val user = getUserByUsername(username)

        return userRepository.save(user)
    }

}