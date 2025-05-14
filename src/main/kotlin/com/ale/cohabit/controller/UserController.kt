package com.ale.cohabit.controller

import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.User
import com.ale.cohabit.service.implementations.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val userService: UserServiceImpl
) {

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Int): ResponseEntity<UserDto?> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }

    @GetMapping("/name/{name}")
    fun getUserById(@PathVariable name: String): ResponseEntity<UserDto?> {
        return ResponseEntity.ok(userService.getUserByUsername(name))
    }

    @GetMapping
    fun getUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(userService.getUsers())
    }

    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<Int> {
        return ResponseEntity.ok(userService.createUser(userDto))
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.deleteUser(userId))
    }
}