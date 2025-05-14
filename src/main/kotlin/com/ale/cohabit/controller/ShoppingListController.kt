package com.ale.cohabit.controller

import com.ale.cohabit.dto.ShoppingListDto
import com.ale.cohabit.dto.TaskDto
import com.ale.cohabit.service.implementations.ShoppingListServiceImpl
import com.ale.cohabit.service.implementations.TaskServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shopping-list")
class ShoppingListController(private val service: ShoppingListServiceImpl) {

    @GetMapping
    fun getShoppingListsByCommunityId(@RequestParam communityId: Int): ResponseEntity<List<ShoppingListDto?>> {
        return ResponseEntity.ok(service.getShoppingListsByCommunityId(communityId))
    }

    @PostMapping
    fun createShoppingList(@RequestParam communityId: Int, @RequestBody shoppingListDto: ShoppingListDto): ResponseEntity<Int> {
        return ResponseEntity.ok(service.createShoppingList(communityId, shoppingListDto))
    }

    @PatchMapping
    fun changeShoppingListStatus(@RequestParam id: Int): ResponseEntity<Boolean> {
        return ResponseEntity.ok(service.changeShoppingListStatus(id))
    }

//    @PutMapping
//    fun modifyTask(@RequestBody taskDto: TaskDto): ResponseEntity<Unit> {
//        try {
//            service.modifyTask(taskDto)
//            return ResponseEntity.ok(Unit)
//        } catch (e: Exception) {
//            throw RuntimeException("Couldn't modify task with id ${taskDto.id}")
//        }
//    }

    @DeleteMapping("/{id}")
    fun deleteShoppingList(@PathVariable id: Int): ResponseEntity<Unit> {
        try {
            service.deleteShoppingList(id)
            return ResponseEntity.noContent().build()
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }
}