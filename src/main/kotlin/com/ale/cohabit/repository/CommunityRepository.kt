package com.ale.cohabit.repository

import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface CommunityRepository: JpaRepository<Community, Int> {
}