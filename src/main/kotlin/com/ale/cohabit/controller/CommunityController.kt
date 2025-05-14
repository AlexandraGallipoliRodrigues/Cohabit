package com.ale.cohabit.controller

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.dto.SimpleCommunityDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.service.implementations.CommunityServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/community")
class CommunityController(
    val communityServiceImpl: CommunityServiceImpl
) {

    @PostMapping
    fun createCommunity(@RequestBody communityDto: CommunityDto): ResponseEntity<Community> {
        return ResponseEntity.ok(communityServiceImpl.createCommunity(communityDto))
    }

    @GetMapping("/{id}")
    fun getCommunityById(@PathVariable id: Int): ResponseEntity<CommunityDto> {
        return ResponseEntity.ok(communityServiceImpl.getCommunityDtoById(id))
    }

    @PatchMapping("/{id}/addUser/{username}")
    fun addUserToCommunity(@PathVariable id: Int, @PathVariable username: String): ResponseEntity<Unit> {
        try {
            communityServiceImpl.addUser(id, username)
            return ResponseEntity.ok(Unit)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }

    @PatchMapping("/{id}/deleteUser/{username}")
    fun deleteUserInCommunity(@PathVariable id: Int, @PathVariable username: String): ResponseEntity<Unit> {
        try {
            communityServiceImpl.deleteUser(id, username)
            return ResponseEntity.ok(Unit)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCommunity(@PathVariable id: Int): ResponseEntity<Unit> {
        try {
            communityServiceImpl.deleteCommunity(id)
            return ResponseEntity.ok().build()
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }
}
