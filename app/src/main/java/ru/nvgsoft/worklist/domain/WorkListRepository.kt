package ru.nvgsoft.worklist.domain

interface WorkListRepository {

    fun getWorkList(): List<WorkItem>

    fun getWorkItem(itemId: Int): WorkItem

    fun deleteWorkItem(workItem: WorkItem)

    fun editWorkItem(workItem: WorkItem)
}