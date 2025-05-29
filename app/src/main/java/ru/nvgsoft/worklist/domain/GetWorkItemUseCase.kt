package ru.nvgsoft.worklist.domain

import javax.inject.Inject

class GetWorkItemUseCase @Inject constructor(private val repository: WorkListRepository) {

    suspend fun getWorkItem(itemId: Int): WorkItem{
        return repository.getWorkItem(itemId)
    }
}