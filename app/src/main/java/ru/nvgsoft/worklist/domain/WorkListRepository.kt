package ru.nvgsoft.worklist.domain

import androidx.lifecycle.LiveData

interface WorkListRepository {

    fun getWorkList(): LiveData<List<WorkItem>>

    fun getWorkItem(itemId: Int): WorkItem

    fun deleteWorkItem(workItem: WorkItem)

    fun editWorkItem(workItem: WorkItem)

    fun addWorkItem(workItem: WorkItem)
}