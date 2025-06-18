package ru.nvgsoft.worklist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.nvgsoft.worklist.domain.work_list.WorkItem
import ru.nvgsoft.worklist.domain.work_list.WorkListRepository
import javax.inject.Inject

class WorkListRepositoryImpl @Inject constructor(
    private val workListDao: WorkListDao,
    private val mapper:Mapper
    ) : WorkListRepository {


    override fun getWorkList(): LiveData<List<WorkItem>> {
        return workListDao.getWorkList().map { mapper.mapListDbModelToListEntity(it) }
    }


    override suspend fun getWorkItem(itemId: Int): WorkItem {
        val workItemDbModel = workListDao.getWorkItem(itemId)
        return mapper.mapDbModelToEntity(workItemDbModel)

    }

    override suspend fun deleteWorkItem(workItem: WorkItem) {
        workListDao.deleteWorkItem(workItem.id)
    }

    override suspend fun editWorkItem(workItem: WorkItem) {
        workListDao.addWorkItem(mapper.mapEntityToDbModel(workItem))
    }

    override suspend fun addWorkItem(workItem: WorkItem) {
       workListDao.addWorkItem(mapper.mapEntityToDbModel(workItem))
    }



}