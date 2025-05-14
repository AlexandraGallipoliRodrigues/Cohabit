package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.dto.SimpleCommunityDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.repository.CommunityRepository
import com.ale.cohabit.service.implementations.mapper.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.collections.map
import kotlin.jvm.optionals.getOrNull

@Service
class CommunityServiceImpl(
    private val communityRepository: CommunityRepository,
    private val userServiceImpl: UserServiceImpl,
    private val mapper: Mapper
) {
    @Transactional
    fun createCommunity(communityDto: CommunityDto): Community {
        var community = mapper.mapDtoToCommunity(communityDto)
        var user = userServiceImpl.findByUsername(communityDto.creatorUsername)
        if (user != null) {
            var userids = mutableListOf(user)
            community.userIds = userids
            user.community = community
            userServiceImpl.updateUser(user)
        } else {
            throw RuntimeException("Couldn't fetch user with username ${community.creatorUsername}")
        }
        community = communityRepository.save(community)
        val communityId = community.id

        if (-1 == communityId) {
            throw Exception("no se ha podido crear la comunidad ${communityDto.name}")
        } else return community
    }

     fun deleteCommunity(communityId: Int) = communityRepository.deleteById(communityId)

    fun getCommunityDtoById(id: Int): CommunityDto? {
        var simpleCommunityDto = communityRepository.findCommunityWithDetails(id)
        if (simpleCommunityDto != null) {
            var users = userServiceImpl.getUsersByCommunityId(id)
            var communityDto = CommunityDto(
                id = simpleCommunityDto.id,
                name = simpleCommunityDto.name,
                creatorUsername = simpleCommunityDto.creatorUsername,
                users = users.filter { u -> u != null}.map { u -> u?.username ?: "" }.toMutableList(),
                tasks = mutableListOf(),
                shoppingLists = mutableListOf()
            )
            return communityDto
        } else {
            throw RuntimeException("Couldn't fetch details of community with id $id")
        }
    }

    @Transactional
    fun addUser(id: Int, username: String) {
        var community = communityRepository.findById(id).getOrNull()
        if (community != null) {
            var user = userServiceImpl.findByUsername(username)
            if (user != null) {
                if (user.community != null) {
                    throw RuntimeException("User with username $username already belongs to a community")
                }
                user.community = community
                community.userIds?.add(user)
                communityRepository.save(community)
                userServiceImpl.updateUser(user)
            } else {
                throw RuntimeException("Community with id $id does not exist")
            }
        } else {
            throw RuntimeException("Community with id $id does not exist")
        }
    }

    @Transactional
    fun deleteUser(id: Int, username: String) {
        var community = communityRepository.findById(id).getOrNull()
        if (community != null) {
            var user = userServiceImpl.findByUsername(username)
            if (user != null) {
                if (user.community == null) {
                    throw RuntimeException("User with username $username does not belong to the community")
                }
                user.community = null
                community.userIds?.remove(user)
                userServiceImpl.updateUser(user)
                communityRepository.save(community)
            } else {
                throw RuntimeException("Community with id $id does not exist")
            }
        } else {
            throw RuntimeException("Community with id $id does not exist")
        }
    }

    fun findCommunityById(id: Int): Community? = communityRepository.findById(id).getOrNull()

}