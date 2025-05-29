package ru.nvgsoft.worklist.domain

import javax.inject.Inject

class EditWorkItemUSeCase @Inject constructor(private val repository: WorkListRepository) {

    suspend fun editWorkItem(workItem: WorkItem){
        repository.editWorkItem(workItem)
    }
}