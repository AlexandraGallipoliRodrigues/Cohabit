package com.ale.cohabit.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class UserDto(
    val id: Int,
    var name: String? = null,
    val surname: String? = null,
    var username: String,
    val email: String,
    var communityName: String,
    var communityId: Int
)