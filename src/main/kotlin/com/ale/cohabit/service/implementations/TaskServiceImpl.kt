package com.ale.cohabit.service.implementations

import com.ale.cohabit.dto.TaskDto
import com.ale.cohabit.entity.Task
import com.ale.cohabit.repository.TaskRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val communityServiceImpl: CommunityServiceImpl,
    private val userServiceImpl: UserServiceImpl
    ) {

    fun getTasksByCommunityId(id: Int): List<TaskDto?> {
        return taskRepository.getTasksDtoByCommunityId(id)
    }

    fun createTask(communityId: Int, taskDto: TaskDto): Int? {
        val community = communityServiceImpl.findCommunityById(communityId)
        if (community == null) {
            throw RuntimeException("Community with id $communityId does not exist")
        }
        var assignedUser = userServiceImpl.findByUsername(taskDto.assignee)
        if (assignedUser == null) {
            throw RuntimeException("Assignee does not exist")
        }
        var task = Task(
            id = null,
            name = taskDto.name,
            checked = false,
            deadline = taskDto.deadline,
            assignee = taskDto.assignee,
            community = community
        )
        taskRepository.save(task)
        return task.id
    }

    fun changeTaskStatus(taskId: Int): Boolean {
        val task = taskRepository.findById(taskId).getOrNull()
        if (task != null) {
            task.checked = !task.checked
            taskRepository.save(task)
            return true
        } else {
            throw RuntimeException("Task with id $taskId does not exist")
        }
    }

    fun modifyTask(taskDto: TaskDto) {
        var task = taskRepository.findById(taskDto.id!!).getOrNull()
        if (task != null) {
            task.name = taskDto.name
            task.deadline = taskDto.deadline
            task.assignee = taskDto.assignee

        } else {
            throw RuntimeException("Task with id ${taskDto.id} does not exist")
        }

    }
    fun deleteTask(id: Int) = taskRepository.deleteById(id)

}