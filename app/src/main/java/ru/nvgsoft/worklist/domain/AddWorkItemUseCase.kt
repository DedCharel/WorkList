package ru.nvgsoft.worklist.domain

class AddWorkItemUseCase(private val repository: WorkListRepository) {

    fun addWorkItem(workItem: WorkItem){
        repository.addWorkItem(workItem)
    }
}