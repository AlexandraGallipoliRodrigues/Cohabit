package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.repository.CommunityRepository
import com.ale.cohabit.service.implementations.mapper.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityServiceImpl(
    private val communityRepository: CommunityRepository,
    private val mapper: Mapper
) {
    @Transactional
    fun createCommunity(communityDto: CommunityDto): Community {
        var community = mapper.mapDtoToCommunity(communityDto)

        community = communityRepository.save(community)
        val communityId = community.id

        if (-1 == communityId) {
            throw Exception("no se ha podido crear la comunidad ${communityDto.name}")
        } else return community
    }

     fun deleteCommunity(communityId: Int) = communityRepository.deleteById(communityId)


}