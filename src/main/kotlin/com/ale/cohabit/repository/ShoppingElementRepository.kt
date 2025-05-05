package com.ale.cohabit.repository

import com.ale.cohabit.entity.ShoppingElement
import org.springframework.data.jpa.repository.JpaRepository

interface ShoppingElementRepository: JpaRepository<ShoppingElement, Int> {
}