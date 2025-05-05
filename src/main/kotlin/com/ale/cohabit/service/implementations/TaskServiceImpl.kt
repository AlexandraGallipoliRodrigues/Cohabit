package com.ale.cohabit.service.implementations

import com.ale.cohabit.repository.CommunityRepository
import com.ale.cohabit.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(private val taskRepository: TaskRepository) {
}