package ru.nvgsoft.worklist.domain.work_list

import javax.inject.Inject

class DeleteWorkItemUSeCase @Inject constructor(private val repository: WorkListRepository) {

    suspend fun deleteWorkItem(workItem: WorkItem){
        repository.deleteWorkItem(workItem)
    }
}