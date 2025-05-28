package ru.nvgsoft.worklist.domain

class GetWorkItemUseCase(private val repository: WorkListRepository) {

    suspend fun getWorkItem(itemId: Int): WorkItem{
        return repository.getWorkItem(itemId)
    }
}