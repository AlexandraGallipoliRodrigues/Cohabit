package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.ShoppingListDto
import com.ale.cohabit.entity.ShoppingElement
import com.ale.cohabit.entity.ShoppingList
import com.ale.cohabit.repository.ShoppingListRepository
import com.ale.cohabit.service.implementations.mapper.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class ShoppingListServiceImpl(
    private val shoppingListRepository: ShoppingListRepository,
    private val communityServiceImpl: CommunityServiceImpl,
    private val shoppingElementServiceImpl: ShoppingElementServiceImpl,
    private val mapper: Mapper
) {

    fun getShoppingListsByCommunityId(communityId: Int): List<ShoppingListDto>? {
        val shoppingLists = shoppingListRepository.findByCommunityId(communityId)
        var shoppingListsDto = mutableListOf<ShoppingListDto>()
        for (shoppingList in shoppingLists) {
            shoppingListsDto.add(mapper.mapShoppingListToDto(shoppingList))
        }
        return shoppingListsDto
    }

    @Transactional
    fun createShoppingList(communityId: Int, shoppingListDto: ShoppingListDto): Int {
        val community = communityServiceImpl.findCommunityById(communityId)
        if (community == null) {
            throw RuntimeException("Couldn't find community with id $communityId")
        }
        var shoppingList = ShoppingList(
            id = null,
            name = shoppingListDto.name,
            checked = false,
            elements = mutableListOf(),
            community = community
        )
        var shoppingElements = mutableListOf<ShoppingElement>()
        for (shoppingElementDto in shoppingListDto.elements) {
            shoppingElements.add(shoppingElementServiceImpl.createShoppingElement(shoppingList, shoppingElementDto))
        }
        shoppingList.elements = shoppingElements
        shoppingList = shoppingListRepository.save(shoppingList)
        if (shoppingList.id != null) {
            shoppingElementServiceImpl.updateShoppingElements(shoppingList, shoppingElements)
            return shoppingList.id!!
        } else {
            throw RuntimeException("Couldn't create shopping list with name ${shoppingList.name}")
        }
    }

    fun changeShoppingListStatus(id: Int): Boolean {
        val shoppingList = shoppingListRepository.findById(id).getOrNull()
        if (shoppingList != null) {
            shoppingList.checked = !shoppingList.checked
            shoppingListRepository.save(shoppingList)
            return true
        } else {
            throw RuntimeException("Task with id $id does not exist")
        }
    }

    fun deleteShoppingList(id: Int) = shoppingListRepository.deleteById(id)
}