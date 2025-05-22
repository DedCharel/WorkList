package ru.nvgsoft.worklist.data

import ru.nvgsoft.worklist.domain.WorkItem
import ru.nvgsoft.worklist.domain.WorkListRepository

class WorkListRepositoryImpl:WorkListRepository {
    private val workList = mutableListOf<WorkItem>()

    override fun getWorkList(): List<WorkItem> {
        return workList.toList()
    }

    override fun getWorkItem(itemId: Int): WorkItem {
       return workList.find { it.id == itemId } ?: throw RuntimeException("getWorkItem: itemId = null")
    }

    override fun deleteWorkItem(workItem: WorkItem) {
        workList.remove(workItem)
    }

    override fun editWorkItem(workItem: WorkItem) {
        val itemId = workItem.id
        val oldItem = getWorkItem(itemId)
        workList.remove(oldItem)
        workList.add(workItem)


    }
}