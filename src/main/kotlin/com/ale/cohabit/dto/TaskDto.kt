package com.ale.cohabit.dto

import com.ale.cohabit.entity.Community
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter

@NoArgsConstructor
open class TaskDto(
    val id: Int,
    val name: String,
    var checked: Boolean,
    var deadline: String,
    var assignee: Int
)