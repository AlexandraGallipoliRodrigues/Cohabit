package com.ale.cohabit.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class UserDto(
    val id: Int?,
    val name: String?,
    val surname: String?,
    val email: String?,
    val comunidad: String? = DEFAULT_COMUNITY_NAME + name

) {
    companion object {
        private const val DEFAULT_COMUNITY_NAME = "Comunidad de "
    }
}