package com.ale.cohabit.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class ShoppingListDto(
    val id: Int,
    val name: String,
    var elements: MutableList<ShoppingElementDto>,
    var checked: Boolean
)