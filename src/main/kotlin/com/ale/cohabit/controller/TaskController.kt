package com.ale.cohabit.controller

import com.ale.cohabit.dto.TaskDto
import com.ale.cohabit.service.implementations.TaskServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/task")
class TaskController(private val service: TaskServiceImpl) {

    @GetMapping
    fun getTasksByCommunityId(@RequestParam communityId: Int): ResponseEntity<List<TaskDto?>> {
        return ResponseEntity.ok(service.getTasksByCommunityId(communityId))
    }

    @PostMapping
    fun createTask(@RequestParam communityId: Int, @RequestBody taskDto: TaskDto): ResponseEntity<Int> {
        return ResponseEntity.ok(service.createTask(communityId, taskDto))
    }

    @PatchMapping
    fun changeTaskStatus(@RequestParam id: Int): ResponseEntity<Boolean> {
        return ResponseEntity.ok(service.changeTaskStatus(id))
    }

    @PutMapping
    fun modifyTask(@RequestBody taskDto: TaskDto): ResponseEntity<Unit> {
        try {
            service.modifyTask(taskDto)
            return ResponseEntity.ok(Unit)
        } catch (e: Exception) {
            throw RuntimeException("Couldn't modify task with id ${taskDto.id}")
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Int): ResponseEntity<Unit> {
        try {
            service.deleteTask(id)
            return ResponseEntity.noContent().build()
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }
}