package ru.nvgsoft.worklist.domain

class GetWorkItemUseCase(private val repository: WorkListRepository) {

    fun getWorkItem(itemId: Int): WorkItem{
        return repository.getWorkItem(itemId)
    }
}