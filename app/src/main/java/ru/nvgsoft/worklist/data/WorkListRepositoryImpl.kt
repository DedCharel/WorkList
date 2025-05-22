package ru.nvgsoft.worklist.data

import android.icu.util.Calendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nvgsoft.worklist.domain.WorkItem
import ru.nvgsoft.worklist.domain.WorkListRepository

class WorkListRepositoryImpl : WorkListRepository {
    private val workList = mutableListOf<WorkItem>()

    private val listLD = MutableLiveData<List<WorkItem>>()

    private var autoIncrementId = 0

    init {
        repeat(10) {
            addWorkItem(
                WorkItem(
                    date = Calendar.getInstance().timeInMillis,
                    worker = "Worker $it",
                    organisation = "Organisation $it",
                    description = "Description $it",
                    spendTime = it.toDouble()
                )
            )
        }
    }

    override fun getWorkList(): LiveData<List<WorkItem>> {
        return listLD
    }

    override fun getWorkItem(itemId: Int): WorkItem {
        return workList.find { it.id == itemId }
            ?: throw RuntimeException("getWorkItem: itemId = null")

    }

    override fun deleteWorkItem(workItem: WorkItem) {
        workList.remove(workItem)
        updateList()
    }

    override fun editWorkItem(workItem: WorkItem) {
        val itemId = workItem.id
        val oldItem = getWorkItem(itemId)
        workList.remove(oldItem)
        addWorkItem(workItem)
    }

    override fun addWorkItem(workItem: WorkItem) {
        if (workItem.id == WorkItem.UNDEFINED_ID) {
            workItem.id = autoIncrementId++
        }
        workList.add(workItem)
        updateList()
    }

    fun updateList(){
        listLD.value = workList.toList()
    }
}