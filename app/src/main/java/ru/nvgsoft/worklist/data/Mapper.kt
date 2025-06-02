package ru.nvgsoft.worklist.data

import ru.nvgsoft.worklist.domain.organization.Organization
import ru.nvgsoft.worklist.domain.work_list.WorkItem
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


    fun mapDbModelToEntity(dbModel: WorkItemDbModel): WorkItem {
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

    fun mapEntityToDbModel(organization: Organization): OrganizationDbModel {
        return OrganizationDbModel(
            id = organization.id,
            name = organization.name,
            email = organization.email,
            phone = organization.phone
        )
    }

    fun mapDbModelToEntity(dbModel: OrganizationDbModel): Organization {
        return Organization(
            id = dbModel.id,
            name = dbModel.name,
            email = dbModel.email,
            phone = dbModel.phone
        )
    }
    fun mapListDbModelOrganizationToListEntity(list: List<OrganizationDbModel>): List<Organization>{
        return list.map { mapDbModelToEntity(it) }
    }

}