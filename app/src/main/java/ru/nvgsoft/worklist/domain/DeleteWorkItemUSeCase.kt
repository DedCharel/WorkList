package ru.nvgsoft.worklist.domain

class DeleteWorkItemUSeCase(private val repository: WorkListRepository) {

    suspend fun deleteWorkItem(workItem: WorkItem){
        repository.deleteWorkItem(workItem)
    }
}