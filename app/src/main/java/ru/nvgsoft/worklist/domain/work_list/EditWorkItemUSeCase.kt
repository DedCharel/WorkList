package ru.nvgsoft.worklist.domain.work_list

import javax.inject.Inject

class EditWorkItemUSeCase @Inject constructor(private val repository: WorkListRepository) {

    suspend fun editWorkItem(workItem: WorkItem){
        repository.editWorkItem(workItem)
    }
}