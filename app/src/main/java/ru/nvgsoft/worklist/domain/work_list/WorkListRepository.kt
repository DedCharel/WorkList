package ru.nvgsoft.worklist.domain.work_list

import androidx.lifecycle.LiveData

interface WorkListRepository {

    fun getWorkList(): LiveData<List<WorkItem>>

    suspend fun getWorkItem(itemId: Int): WorkItem

    suspend fun deleteWorkItem(workItem: WorkItem)

    suspend fun editWorkItem(workItem: WorkItem)

    suspend fun addWorkItem(workItem: WorkItem)
}

