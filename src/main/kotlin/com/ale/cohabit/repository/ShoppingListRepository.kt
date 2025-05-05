package com.ale.cohabit.repository

import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.ShoppingList
import com.ale.cohabit.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ShoppingListRepository: JpaRepository<ShoppingList, Int> {
}