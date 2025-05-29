package ru.nvgsoft.worklist.domain

import javax.inject.Inject

class AddWorkItemUseCase @Inject constructor(private val repository: WorkListRepository) {

   suspend fun addWorkItem(workItem: WorkItem){
        repository.addWorkItem(workItem)
    }
}