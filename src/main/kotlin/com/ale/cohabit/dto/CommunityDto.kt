package com.ale.cohabit.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class CommunityDto(
    val id: Int?,
    val name: String,
    val creatorUsername: String,
    var userIds: MutableList<UserDto>,
    var tasks: MutableList<TaskDto>,
    var shoppingLists: MutableList<ShoppingListDto>,
    )