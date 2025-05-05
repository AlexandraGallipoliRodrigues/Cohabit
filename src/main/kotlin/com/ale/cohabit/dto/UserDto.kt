package com.ale.cohabit.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class UserDto(
    val id: Int? = null,
    var name: String? = null,
    val surname: String? = null,
    var username: String? = null,
    val email: String? = null,
    var comunidad: String? = null
)