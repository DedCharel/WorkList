package ru.nvgsoft.worklist.domain

class EditWorkItemUSeCase(private val repository: WorkListRepository) {

    fun editWorkItem(workItem: WorkItem){
        repository.editWorkItem(workItem)
    }
}