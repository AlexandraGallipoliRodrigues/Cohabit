package com.ale.cohabit.repository

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.dto.SimpleCommunityDto
import com.ale.cohabit.entity.Community
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CommunityRepository: JpaRepository<Community, Int> {

    @Query("""
        SELECT new com.ale.cohabit.dto.SimpleCommunityDto(
            c.id, 
            c.name, 
            c.creatorUsername)
        FROM Community c
        WHERE c.id = ?1
    """)
    fun findCommunityWithDetails(id: Int): SimpleCommunityDto?
}