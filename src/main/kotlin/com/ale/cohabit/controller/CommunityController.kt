package com.ale.cohabit.controller

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.User
import com.ale.cohabit.service.implementations.CommunityServiceImpl
import com.ale.cohabit.service.implementations.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/community")
class CommunityController(val communityServiceImpl: CommunityServiceImpl
) {

    @PostMapping
    fun createCommunity(@RequestBody communityDto: CommunityDto): ResponseEntity<Community> {
        return ResponseEntity.ok(communityServiceImpl.createCommunity(communityDto))
    }
}