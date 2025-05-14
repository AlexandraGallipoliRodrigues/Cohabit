package com.ale.cohabit.repository

import com.ale.cohabit.dto.TaskDto
import com.ale.cohabit.entity.Community
import com.ale.cohabit.entity.Task
import com.ale.cohabit.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TaskRepository: JpaRepository<Task, Int> {

    @Query("SELECT new com.ale.cohabit.dto.TaskDto(t.id, t.name, t.checked, t.deadline, t.assignee) FROM Task t WHERE t.community.id = ?1")
    fun getTasksDtoByCommunityId(communityId: Int): List<TaskDto?>
}