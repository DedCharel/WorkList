package ru.nvgsoft.worklist.data

import ru.nvgsoft.worklist.domain.WorkItem
import javax.inject.Inject

class Mapper @Inject constructor(){

    fun mapEntityToDbModel(workItem: WorkItem): WorkItemDbModel {
        return WorkItemDbModel(
            id = workItem.id,
            date = workItem.date,
            worker = workItem.worker,
            organisation = workItem.organisation,
            description = workItem.description,
            spendTime = workItem.spendTime
        )
    }


    fun mapDbModelToEntity(dbModel: WorkItemDbModel): WorkItem{
        return WorkItem(
            id = dbModel.id,
            date = dbModel.date,
            worker = dbModel.worker,
            organisation = dbModel.organisation,
            description = dbModel.description,
            spendTime = dbModel.spendTime
        )
    }

    fun mapListDbModelToListEntity(list: List<WorkItemDbModel>): List<WorkItem>{
        return list.map { mapDbModelToEntity(it) }
    }
}