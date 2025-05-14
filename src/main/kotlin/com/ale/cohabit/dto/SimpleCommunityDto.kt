package com.ale.cohabit.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class SimpleCommunityDto(
    val id: Int?,
    val name: String,
    val creatorUsername: String
)