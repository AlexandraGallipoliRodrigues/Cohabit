package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.repository.CommunityRepository
import org.springframework.stereotype.Service

@Service
class CommunityServiceImpl(
    private val communityRepository: CommunityRepository,
    private val userServiceImpl: UserServiceImpl
) {

    fun createCommunity(communityDto: CommunityDto): Int {
        var community = Community(id = null, name = communityDto.name, userIds = mutableListOf())
        community = communityRepository.save(community)
        var user = userServiceImpl.assignCommunityToUser(communityDto.creatorUsername, community)
        community.userIds.add(user)
        community = communityRepository.save(community)
        return community.id ?: -1
    }

     fun deleteCommunity(communityId: Int) = communityRepository.deleteById(communityId)


}