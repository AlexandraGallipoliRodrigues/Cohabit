package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.ShoppingElementDto
import com.ale.cohabit.entity.ShoppingElement
import com.ale.cohabit.entity.ShoppingList
import com.ale.cohabit.repository.ShoppingElementRepository
import com.ale.cohabit.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class ShoppingElementServiceImpl(private val shoppingElementRepository: ShoppingElementRepository) {

    @Transactional
    fun createShoppingElement(shoppingList: ShoppingList, shoppingElementDto: ShoppingElementDto): ShoppingElement {
        var shoppingElement = ShoppingElement(
            id = null,
            name = shoppingElementDto.name,
            checked = false,
            shoppingList = null
        )
        shoppingElement = shoppingElementRepository.save(shoppingElement)
        if (shoppingElement.id != null) {
            return shoppingElement
        } else {
            throw RuntimeException("Couldn't create shopping element ${shoppingElementDto.name} in ${shoppingList.name}")
        }
    }

    @Transactional
    fun updateShoppingElements(shoppingList: ShoppingList, shoppingElements: List<ShoppingElement>) {
        for (element in shoppingElements) {
            element.shoppingList = shoppingList
            shoppingElementRepository.save(element)
        }
    }


}