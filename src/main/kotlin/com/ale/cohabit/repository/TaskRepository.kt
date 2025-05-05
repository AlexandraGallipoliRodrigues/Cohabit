package com.ale.cohabit.repository

import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.Task
import com.ale.cohabit.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Int> {
}