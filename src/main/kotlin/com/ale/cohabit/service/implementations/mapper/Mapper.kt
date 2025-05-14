package com.ale.cohabit.service.implementations.mapper

import com.ale.cohabit.dto.CommunityDto
import com.ale.cohabit.dto.ShoppingElementDto
import com.ale.cohabit.dto.ShoppingListDto
import com.ale.cohabit.dto.TaskDto
import com.ale.cohabit.dto.UserDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.ShoppingElement
import com.ale.cohabit.entity.ShoppingList
import com.ale.cohabit.entity.Task
import com.ale.cohabit.entity.User
import org.springframework.stereotype.Component

@Component
class Mapper {

    fun mapUserToDto(user: User): UserDto {
        return UserDto(
            id = user.id ?: -1,
            name = user.name,
            surname = user.surname,
            username = user.username,
            email = user.email,
            communityId = user.community?.id ?: null,
            communityName = user.community?.name ?: "Comunidad de ${user.name}"

        )
    }

    fun mapDtoToUser(user: UserDto, community: CommunityDto): User {
        return User(
            id = user.id,
            name = user.name,
            surname = user.surname,
            username = user.username,
            email = user.email,
            community = null,
        )
    }

    fun mapTaskToDto(task: Task): TaskDto {
        return TaskDto(
            id = task.id!!,
            name = task.name,
            checked = task.checked,
            deadline = task.deadline,
            assignee = task.assignee
        )
    }

    fun mapDtoToTask(task: TaskDto, community: CommunityDto): Task{
        return Task(
            id = task.id,
            name = task.name,
            checked = task.checked ?: false,
            deadline = task.deadline,
            assignee = task.assignee,
            community = mapDtoToCommunity(community)
        )
    }

    fun mapDtoToShoppingElement(shoppingElement: ShoppingElementDto, shoppingListDto: ShoppingListDto, community: CommunityDto): ShoppingElement {
        return ShoppingElement(
            id = shoppingElement.id,
            name = shoppingElement.name,
            checked = shoppingElement.checked == true,
            shoppingList = mapDtoToShoppingList(shoppingListDto, community)
        )
    }

    fun mapShoppingElementToDto(shoppingElement: ShoppingElement): ShoppingElementDto {
        return ShoppingElementDto(
            id = shoppingElement.id,
            name = shoppingElement.name,
            checked = shoppingElement.checked
        )
    }

    fun mapShoppingListToDto(shoppingList: ShoppingList): ShoppingListDto {
        return ShoppingListDto(
            id = shoppingList.id!!,
            name = shoppingList.name,
            elements = shoppingList.elements.map { mapShoppingElementToDto(it) }.toMutableList(),
            checked = shoppingList.checked
        )
    }

    fun mapDtoToShoppingList(shoppingList: ShoppingListDto, community: CommunityDto): ShoppingList {
        return ShoppingList(
            id = shoppingList.id,
            name = shoppingList.name,
            elements = shoppingList.elements.map {
                mapDtoToShoppingElement(it, shoppingList, community)
            }.toMutableList(),
            checked = false,
            community = mapDtoToCommunity(community)
        )
    }

    fun mapDtoToCommunity(community: CommunityDto): Community {
        return Community(
            id = community.id,
            name = community.name,
            creatorUsername = community.creatorUsername,
            tasks = mutableListOf(),
            shoppingLists = mutableListOf(),
            userIds = mutableListOf()
        )
    }

}