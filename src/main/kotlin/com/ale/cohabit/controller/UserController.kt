package com.ale.cohabit.controller

import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.service.implementations.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
) {

    val userService = UserServiceImpl()

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Int): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }
    @GetMapping
    fun getUsers(): ResponseEntity<MutableList<UserDto>> {
        return ResponseEntity.ok(userService.getUsers())
    }

    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<Int> {
        return ResponseEntity.ok(userService.createUser(userDto))
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Int): ResponseEntity<Int> {
        return ResponseEntity.ok(userService.deleteUser(userId))
    }
}