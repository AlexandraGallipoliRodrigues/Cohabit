package com.ale.cohabit.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class ShoppingElementDto(
    val id: Int?,
    val name: String,
    var checked: Boolean? = false
)